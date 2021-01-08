package br.com.compasso.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface IService<T, I> {

    Optional<T> findById(I id);

    T findByIdOrException(I id);

    T insert(T t);

    T update(T t, I id);

    boolean deleteById(I id);

    Page<T> findAll(Pageable pageRequest);

    List<T> findAll();

    void validateBeforeInsert(T ent);

    void beforeInsert(T ent);

    void validateBeforeUpdate(T ent);

    void beforeUpdate(T ent);

    T afterInsert(T ent);

    T afterUpdate(T ent);

    void afterDelete(T ent);

    void beforeDelete(T ent);
}
