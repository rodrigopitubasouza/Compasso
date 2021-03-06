package br.com.compasso.backend.service;

import br.com.compasso.backend.dto.IdDto;
import br.com.compasso.backend.entity.IdEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface IService<T extends IdEntity, G extends IdDto,  P extends IdDto,  U extends IdDto, A extends IdDto, S extends Specification<T>, I> {

    Optional<T> findById(I id);

    G findByIdOrException(I id);

    G insert(P t);

    G update(U t, I id);

    G patch(A t, I id);

    boolean deleteById(I id);

    Page<G> findAll(S search,Pageable pageRequest);

    List<G> findAll(S search);

    void validateBeforeInsert(T ent);

    void beforeInsert(T ent);

    void validateBeforeUpdate(T ent);

    void beforeUpdate(T ent);

    T afterInsert(T ent);

    T afterUpdate(T ent);

    void afterDelete(T ent);

    void beforeDelete(T ent);

    void treatJoinColumnsInsert(T ent, P dto);

    void treatJoinColumnsUpdate(T ent, U dto);

    void treatJoinColumnsPatch(T ent, A dto);
}
