package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.model.BaseEntity;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.validation.exception.ServiceValidationException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import java.io.Serializable;
import java.util.*;

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
        validateEntity(entity);
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
            validateEntity(entity);
            result = wrapJPACallToOptional(() -> repository.save(entity));
        } else {
            result = Optional.empty();
        }
        return result;
    }

    @Override
    public Iterable<E> findAll() {
        return wrapJPACall(() -> repository.findAll());
    }

    protected void validateEntity(E entity) {
        validate(entity, getEntityName());
    }

    protected void validate(Object o, String name) {
        BindingResult result = new BeanPropertyBindingResult(o, name);
        for(Validator validator : getValidators()){
            if (validator.supports(o.getClass())){
                validator.validate(o, result);
            }
        }
        if (result.hasErrors()){
            throw new ServiceValidationException(result);
        }
    }

    protected abstract String getEntityName();

    protected List<Validator> getValidators() {
        return Collections.emptyList();
    }

}
