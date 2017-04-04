package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.BaseEntity;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.service.exception.ServiceException;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.Optional;

import static by.triumgroup.recourse.service.exception.wrapper.ServiceExceptionWrapper.tryCallJPA;
import static by.triumgroup.recourse.service.util.RepositoryCallWrapper.wrapToBoolean;
import static by.triumgroup.recourse.service.util.RepositoryCallWrapper.wrapToOptional;

public abstract class AbstractCrudService<E extends BaseEntity<ID>, ID extends Serializable> implements CrudService<E, ID> {

    private final CrudRepository<E, ID> repository;

    protected AbstractCrudService(CrudRepository<E, ID> repository) {
        this.repository = repository;
    }

    @Override
    public Optional<E> findById(ID id) throws ServiceException {
        return wrapToOptional(() -> repository.findOne(id));
    }

    @Override
    public <S extends E> Optional<S> add(S entity) throws ServiceException {
        entity.setId(null);
        return wrapToOptional(() -> repository.save(entity));
    }

    @Override
    public Optional<Boolean> delete(ID id) throws ServiceException {
        return wrapToBoolean(() -> repository.delete(id));
    }

    @Override
    public <S extends E> Optional<S> update(S entity, ID id) throws ServiceException {
        Optional<S> result;
        if (tryCallJPA(() -> repository.exists(id))){
            entity.setId(id);
            result = wrapToOptional(() -> repository.save(entity));
        } else {
            result = Optional.empty();
        }
        return result;
    }
}
