package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.CrudController;
import by.triumgroup.recourse.controller.exception.BadRequestException;
import by.triumgroup.recourse.controller.exception.ControllerException;
import by.triumgroup.recourse.controller.exception.NotFoundException;
import by.triumgroup.recourse.entity.BaseEntity;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.service.exception.ServiceException;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Optional;

public abstract class AbstractCrudController<E extends BaseEntity<ID>, ID> implements CrudController<E,ID> {

    private final Logger logger;
    private final CrudService<E, ID> crudService;

    AbstractCrudController(CrudService<E, ID> crudService, Logger logger) {
        this.logger = logger;
        this.crudService = crudService;
    }

    public E getById(@PathVariable("id") ID id) throws ControllerException {
        try {
            Optional<E> entity = crudService.findById(id);
            return entity.orElseThrow(NotFoundException::new);
        } catch (ServiceException e) {
            logger.warn("Error while retrieving entity with id " + id + "\n", e);
            throw new ControllerException(e);
        }
    }

    @Override
    public <S extends E> S create(@Valid @RequestBody S entity) throws ControllerException {
        try {
            Optional<S> newEntity = crudService.add(entity);
            return newEntity.orElseThrow(BadRequestException::new);
        } catch (ServiceException e) {
            logger.warn("Cannot create entity with \n" + entity + "\n", e);
            throw new ControllerException(e);
        }
    }

    @Override
    public <S extends E> S update(@Valid @RequestBody S entity, @PathVariable("id") ID id) throws ControllerException {
        try {
            Optional<S> updatedEntity = crudService.update(entity, id);
            return updatedEntity.orElseThrow(NotFoundException::new);
        } catch (ServiceException e) {
            logger.warn("Cannot update entity with \n" + entity + "\n", e);
            throw new ControllerException(e);
        }
    }

    @Override
    public void delete(@PathVariable("id") ID id) throws ControllerException {
        try {
            crudService.delete(id).orElseThrow(NotFoundException::new);
        } catch (ServiceException e) {
            logger.warn("Cannot delete entity with id " + id + "\n", e);
            throw new ControllerException(e);
        }
    }
}
