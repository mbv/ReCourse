package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.BaseEntity;
import by.triumgroup.recourse.service.CrudService;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.Optional;

import static by.triumgroup.recourse.util.RepositoryCallWrapper.*;

public abstract class AbstractCrudService<E extends BaseEntity<ID>, ID extends Serializable> implements CrudService<E, ID> {

    private final CrudRepository<E, ID> repository;

    protected AbstractCrudService(CrudRepository<E, ID> repository) {
        this.repository = repository;
    }

    @Override
    public Optional<E> findById(ID id) {
        return wrapJPACallToOptional(() -> repository.findOne(id));
    }

    @Override
    public <S extends E> Optional<S> add(S entity) {
        entity.setId(null);
        return wrapJPACallToOptional(() -> repository.save(entity));
    }

    @Override
    public Optional<Boolean> delete(ID id) {
        return wrapJPACallToBoolean(() -> repository.delete(id));
    }

    @Override
    public <S extends E> Optional<S> update(S entity, ID id) {
        Optional<S> result;
        if (wrapJPACall(() -> repository.exists(id))) {
            entity.setId(id);
            result = wrapJPACallToOptional(() -> repository.save(entity));
        } else {
            result = Optional.empty();
        }
        return result;
    }
}
