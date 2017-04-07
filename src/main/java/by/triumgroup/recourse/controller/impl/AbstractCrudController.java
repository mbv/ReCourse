package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.CrudController;
import by.triumgroup.recourse.controller.exception.BadRequestException;
import by.triumgroup.recourse.controller.exception.NotFoundException;
import by.triumgroup.recourse.entity.BaseEntity;
import by.triumgroup.recourse.service.CrudService;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Optional;

import static by.triumgroup.recourse.util.ServiceCallWrapper.wrapServiceCall;

public abstract class AbstractCrudController<E extends BaseEntity<ID>, ID> implements CrudController<E,ID> {

    private final Logger logger;
    private final CrudService<E, ID> crudService;

    AbstractCrudController(CrudService<E, ID> crudService, Logger logger) {
        this.logger = logger;
        this.crudService = crudService;
    }

    public E getById(@PathVariable("id") ID id) {
        return wrapServiceCall(logger, () -> {
            Optional<E> callResult = crudService.findById(id);
            return callResult.orElseThrow(NotFoundException::new);
        });
    }

    @Override
    public <S extends E> S create(@Valid @RequestBody S entity) {
        return wrapServiceCall(logger, () -> {
            Optional<S> callResult = crudService.add(entity);
            return callResult.orElseThrow(BadRequestException::new);
        });
    }

    @Override
    public <S extends E> S update(@Valid @RequestBody S entity, @PathVariable("id") ID id) {
        return wrapServiceCall(logger, () -> {
            Optional<S> callResult = crudService.update(entity, id);
            return callResult.orElseThrow(NotFoundException::new);
        });
    }

    @Override
    public void delete(@PathVariable("id") ID id) {
        wrapServiceCall(logger, () -> crudService.delete(id).orElseThrow(NotFoundException::new));
    }
}
