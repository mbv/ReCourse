package by.triumgroup.recourse.service;

import by.triumgroup.recourse.entity.BaseEntity;
import by.triumgroup.recourse.service.exception.ServiceException;

import java.util.Optional;

public interface CrudService<E extends BaseEntity<ID>, ID> {

    Optional<E> findById(ID id) throws ServiceException;

    <S extends E> Optional<S> add(S entity) throws ServiceException;

    <S extends E> Optional<S> update(S entity, ID id) throws ServiceException;

    Optional<Boolean> delete(ID id) throws ServiceException;

}
