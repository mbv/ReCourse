package by.triumgroup.recourse.service;

import by.triumgroup.recourse.service.exception.ServiceException;

public interface CrudService<E, ID> {

    E findById(ID id) throws ServiceException;

    <S extends E> S save(S entity) throws ServiceException;

    void delete(ID id) throws ServiceException;

}
