package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.configuration.security.Auth;
import by.triumgroup.recourse.configuration.security.UserAuthDetails;
import by.triumgroup.recourse.controller.CrudController;
import by.triumgroup.recourse.controller.exception.AccessDeniedException;
import by.triumgroup.recourse.controller.exception.BadRequestException;
import by.triumgroup.recourse.controller.exception.NotFoundException;
import by.triumgroup.recourse.entity.model.BaseEntity;
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

    protected abstract boolean hasAuthorityToPerform(E entity, UserAuthDetails authDetails);

    private void checkAuthority(E entity, UserAuthDetails authDetails) {
        if (!authDetails.isAdmin() && !hasAuthorityToPerform(entity, authDetails)) {
            throw new AccessDeniedException();
        }
    }

    public E getById(@PathVariable("id") ID id) {
        return wrapServiceCall(logger, () -> {
            Optional<E> callResult = crudService.findById(id);
            return callResult.orElseThrow(NotFoundException::new);
        });
    }

    public Iterable<E> getAll() {
        return wrapServiceCall(logger, crudService::findAll);
    }

    @Override
    public <S extends E> S create(@Valid @RequestBody S entity, @Auth UserAuthDetails authDetails) {
        checkAuthority(entity, authDetails);
        return wrapServiceCall(logger, () -> {
            Optional<S> callResult = crudService.add(entity);
            return callResult.orElseThrow(BadRequestException::new);
        });
    }

    @Override
    public <S extends E> S update(@Valid @RequestBody S entity, @PathVariable("id") ID id, @Auth UserAuthDetails authDetails) {
        checkAuthority(entity, authDetails);
        return wrapServiceCall(logger, () -> {
            Optional<S> callResult = crudService.update(entity, id);
            return callResult.orElseThrow(NotFoundException::new);
        });
    }

    @Override
    public void delete(@PathVariable("id") ID id, @Auth UserAuthDetails authDetails) {
        checkAuthority(getById(id), authDetails);
        wrapServiceCall(logger, () -> crudService.delete(id).orElseThrow(NotFoundException::new));
    }
}
