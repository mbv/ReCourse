package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.service.exception.ServiceException;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

import static by.triumgroup.recourse.service.exception.wrapper.ServiceExceptionWrapper.tryCallJPA;

public abstract class AbstractCrudService<E, ID extends Serializable> implements CrudService<E, ID> {

    private final CrudRepository<E, ID> repositoy;

    protected AbstractCrudService(CrudRepository<E, ID> repositoy) {
        this.repositoy = repositoy;
    }

    @Override
    public E findById(ID id) throws ServiceException {
        return tryCallJPA(() -> repositoy.findOne(id));
    }

    @Override
    public <S extends E> S save(S entity) throws ServiceException {
        return tryCallJPA(() -> repositoy.save(entity));
    }

    @Override
    public void delete(ID id) throws ServiceException {
        tryCallJPA(() -> repositoy.delete(id));
    }
}
