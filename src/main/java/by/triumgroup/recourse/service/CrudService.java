package by.triumgroup.recourse.service;

import by.triumgroup.recourse.entity.model.BaseEntity;
import by.triumgroup.recourse.service.exception.ServiceException;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CrudService<E extends BaseEntity<ID>, ID> {

    Optional<E> findById(ID id) throws ServiceException;

    Iterable<E> findAll(Pageable pageable) throws ServiceException;

    <S extends E> Optional<S> add(S entity) throws ServiceException;

    Optional<E> update(E entity, ID id) throws ServiceException;

    Optional<Boolean> delete(ID id) throws ServiceException;
}
