package br.com.compasso.backend.service;

import br.com.compasso.backend.enums.ErrorMessageEnum;
import br.com.compasso.backend.exception.EntityNotFoundException;
import br.com.compasso.backend.exception.ValidationException;
import br.com.compasso.backend.repository.IRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class AbstractService<T> implements IService<T, Long> {
    protected final IRepository<T> repository;

    private static final Logger log = LoggerFactory.getLogger(AbstractService.class);

    public AbstractService(final IRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    public Optional<T> findById(final Long id) {
        if (Objects.isNull(id))
            throw new ValidationException(ErrorMessageEnum.IDENTIFICADOR_INVALIDO.getMessage());
        return repository.findById(id);
    }

    @Override
    public T findByIdOrException(final Long id) {
        return findById(id).orElseThrow(() -> notFoundById(id));
    }

    @Override
    public T insert(final T entity) {
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
        return afterInsert(entitySaved);
    }

    private T update(final T entity) {
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

        return afterUpdate(entitySaved);
    }

    @Override
    public T update(final T entity, final Long id) {
        findByIdOrException(id);
        return update(entity);
    }

    @Override
    public boolean deleteById(final Long id) {
        final T entity = findByIdOrException(id);
        beforeDelete(entity);
        try {
            log.info("Tentativa de deleção do objeto {} ID {}", entity.getClass().getSimpleName(), id);
            repository.deleteById(id);
            afterDelete(entity);
            log.info("Deleção completa do objeto {} ID {}", entity.getClass().getSimpleName(), id);

            return true;
        } catch (final RuntimeException e) {
            log.error("Erro na deleção do objeto {} ID {}. Error: {}", entity.getClass().getSimpleName(), id, e.getMessage());
            throw e;
        }
    }

    @Override
    public Page<T> findAll(final Pageable pageRequest) {
        return repository.findAll(pageRequest);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
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
    }

    @Override
    public T afterUpdate(final T ent) {
        return ent;
    }

    @Override
    public void beforeUpdate(final T ent) {
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
}
