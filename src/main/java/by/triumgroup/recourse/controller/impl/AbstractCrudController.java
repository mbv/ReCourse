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
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Optional;
import java.util.function.BiFunction;

import static by.triumgroup.recourse.util.ServiceCallWrapper.wrapServiceCall;

public abstract class AbstractCrudController<E extends BaseEntity<ID>, ID> implements CrudController<E, ID> {

    private final Logger logger;
    private final CrudService<E, ID> crudService;

    AbstractCrudController(CrudService<E, ID> crudService, Logger logger) {
        this.logger = logger;
        this.crudService = crudService;
    }

    protected void validateNestedEntities(E entity) {
    }

    protected abstract boolean hasAuthorityToEdit(E entity, UserAuthDetails authDetails);

    protected boolean hasAuthorityToRead(E entity, UserAuthDetails authDetails) {
        return true;
    }

    protected void checkAuthority(E entity, UserAuthDetails authDetails, BiFunction<E, UserAuthDetails, Boolean> authorityChecker) {
        if (!authDetails.isAdmin() && !authorityChecker.apply(entity, authDetails)) {
            throw new AccessDeniedException();
        }
    }

    @Override
    public E getById(@PathVariable("id") ID id, @Auth UserAuthDetails userAuthDetails) {
        return wrapServiceCall(logger, () -> {
            Optional<E> callResult = crudService.findById(id);
            if (callResult.isPresent()) {
                checkAuthority(callResult.get(), userAuthDetails, this::hasAuthorityToRead);
                return callResult.get();
            } else {
                throw new NotFoundException();
            }
        });
    }

    public Iterable<E> getAll(Pageable pageable, @Auth UserAuthDetails authDetails) {
        return wrapServiceCall(logger, () -> crudService.findAll(pageable));
    }

    @Override
    public <S extends E> S create(@Valid @RequestBody S entity, @Auth UserAuthDetails authDetails) {
        validateNestedEntities(entity);
        checkAuthority(entity, authDetails, this::hasAuthorityToEdit);
        return wrapServiceCall(logger, () -> {
            Optional<S> callResult = crudService.add(entity);
            return callResult.orElseThrow(BadRequestException::new);
        });
    }

    @Override
    public E update(@Valid @RequestBody E entity, @PathVariable("id") ID id, @Auth UserAuthDetails authDetails) {
        validateNestedEntities(entity);
        checkAuthority(entity, authDetails, this::hasAuthorityToEdit);
        return wrapServiceCall(logger, () -> {
            Optional<E> callResult = crudService.update(entity, id);
            return callResult.orElseThrow(NotFoundException::new);
        });
    }

    @Override
    public void delete(@PathVariable("id") ID id, @Auth UserAuthDetails authDetails) {
        checkAuthority(getById(id, authDetails), authDetails, this::hasAuthorityToEdit);
        wrapServiceCall(logger, () -> crudService.delete(id).orElseThrow(NotFoundException::new));
    }
}
