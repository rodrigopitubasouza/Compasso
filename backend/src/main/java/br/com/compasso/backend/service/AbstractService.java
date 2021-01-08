package br.com.compasso.backend.service;

import br.com.compasso.backend.dto.IdDto;
import br.com.compasso.backend.entity.IdEntity;
import br.com.compasso.backend.enums.ErrorMessageEnum;
import br.com.compasso.backend.exception.EntityNotFoundException;
import br.com.compasso.backend.exception.ValidationException;
import br.com.compasso.backend.repository.IRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class AbstractService<T extends IdEntity, G extends IdDto,  P extends IdDto,  U extends IdDto, A extends  IdDto> implements IService<T, G, P, U, A, Long> {
    protected final IRepository<T> repository;

    private static final Logger log = LoggerFactory.getLogger(AbstractService.class);

    @Autowired
    ModelMapper modelMapper;

    public AbstractService(final IRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    public Optional<T> findById(final Long id) {
        if (Objects.isNull(id))
            throw new ValidationException(ErrorMessageEnum.IDENTIFICADOR_INVALIDO.getMessage(), HttpStatus.NOT_FOUND);
        return repository.findById(id);
    }

    @Override
    public G findByIdOrException(final Long id) {
        T entity = findByIdOrExceptionEntity(id);
        return convertToDto(entity);
    }

    @Override
    public G insert(final P dto) {
        T entity = convertToEntity(dto);
        validateBeforeInsert(entity);
        beforeInsert(entity);
        T entitySaved = null;
        try {
            log.info("Tentativa de inserção da entidade {} objeto {}", entity.getClass().getSimpleName(), entity);
            entitySaved = repository.save(entity);
            log.info("Inserção completa da entidade {}", entity.getClass().getSimpleName());
        } catch (final RuntimeException e) {
            log.error("Erro na inserção da entidade {} objeto {}. Error: {}", entity.getClass().getSimpleName(), entity, e.getMessage());
            throw e;
        }
        return convertToDto(afterInsert(entitySaved));
    }

    private G update(final T entity) {
        validateBeforeUpdate(entity);
        beforeUpdate(entity);
        T entitySaved = null;
        try {
            log.info("Tentativa de update da entidade {} objeto {}", entity.getClass().getSimpleName(), entity);
            entitySaved = repository.save(entity);
            log.info("Update completo da entidade {}", entity.getClass().getSimpleName());
        } catch (final RuntimeException e) {
            log.error("Erro no update da entidade {} objeto {}. Error: {}", entity.getClass().getSimpleName(), entity, e.getMessage());
            throw e;
        }

        return convertToDto(afterUpdate(entitySaved));
    }

    @Override
    public G update(final U dto, final Long id) {
        findByIdOrException(id);
        dto.setId(id);
        T entity = convertToEntity(dto);
        return update(entity);
    }

    @Override
    public G patch(final A dto, final Long id) {
        T entity = findByIdOrExceptionEntity(id);
        dto.setId(id);
        convertToEntity(dto, entity);
        return update(entity);
    }

    @Override
    public boolean deleteById(final Long id) {
        final T entity = findByIdOrExceptionEntity(id);
        beforeDelete(entity);
        try {
            log.info("Tentativa de deleção do objeto {} ID {}", entity.getClass().getSimpleName(), id);
            repository.delete(entity);
            afterDelete(entity);
            log.info("Deleção completa do objeto {} ID {}", entity.getClass().getSimpleName(), id);

            return true;
        } catch (final RuntimeException e) {
            log.error("Erro na deleção do objeto {} ID {}. Error: {}", entity.getClass().getSimpleName(), id, e.getMessage());
            throw e;
        }
    }

    @Override
    public Page<G> findAll(final Pageable pageRequest) {
       return convertToPageDto(repository.findAll(pageRequest));
    }

    @Override
    public List<G> findAll() {
        return convertToListDto(repository.findAll());
    }

    private String generateDescription() {
        try {
            final String nomeClasse = ((Class<T>) ((ParameterizedType) getClass()
                    .getGenericSuperclass()).getActualTypeArguments()[0]).getTypeName();
            final Class<?> classe = Class.forName(nomeClasse);
            return classe.getSimpleName();
        } catch (Exception e) {
            log.error("Não foi possível criar uma instância da classe utilizada. Erro: {}", e.getMessage());
            return "entidade";
        }
    }

    @Override
    public void beforeDelete(final T ent) {
        ent.setDataExclusao(LocalDateTime.now());
    }

    @Override
    public void afterDelete(final T ent) {
    }

    @Override
    public T afterInsert(final T ent) {
        return ent;
    }

    @Override
    public void beforeInsert(final T ent) {
        ent.setDataInsercao(LocalDateTime.now());
    }

    @Override
    public T afterUpdate(final T ent) {
        return ent;
    }

    @Override
    public void beforeUpdate(final T ent) {
        ent.setDataAlteracao(LocalDateTime.now());
    }

    @Override
    public void validateBeforeInsert(T ent) {
    }

    @Override
    public void validateBeforeUpdate(T ent) {
    }

    protected EntityNotFoundException notFoundById(final Long id) {
        return new EntityNotFoundException(ErrorMessageEnum.ENTIDADE_INEXISTENTE.getMessage(generateDescription(), id));
    }

    private T findByIdOrExceptionEntity(Long id) {
        return findById(id).orElseThrow(() -> notFoundById(id));
    }

    public Page<G> convertToPageDto(Page<T> entities) {
        final Type type = ((Class<G>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1]);
        return entities.map(objectEntity -> modelMapper.map(objectEntity, type));
    }


    private G convertToDto(T entity) {
        final Type type = ((Class<G>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1]);
        return modelMapper.map(entity, type);
    }

    private T convertToEntity(IdDto dto) {
        final Type type = ((Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]);
        return modelMapper.map(dto, type);
    }

    private void convertToEntity(IdDto dto, T entity) {
        modelMapper.map(dto, entity);
    }

    public List<G> convertToListDto(List<T> entities) {
        final Type type = ((Class<G>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1]);
        return modelMapper.map(entities, type);
    }
}
