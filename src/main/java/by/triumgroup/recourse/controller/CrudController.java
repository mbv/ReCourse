package by.triumgroup.recourse.controller;

import by.triumgroup.recourse.configuration.security.Auth;
import by.triumgroup.recourse.configuration.security.UserAuthDetails;
import by.triumgroup.recourse.controller.exception.ControllerException;
import by.triumgroup.recourse.entity.model.BaseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public interface CrudController<E extends BaseEntity<ID>, ID> {

    @GetMapping("{id}")
    E getById(@PathVariable("id") ID id, @Auth UserAuthDetails authDetails) throws ControllerException;

    @GetMapping
    Iterable<E> getAll(Pageable pageable, @Auth UserAuthDetails authDetails) throws ControllerException;

    @PostMapping
    <S extends E> S create(@RequestBody S entity, @Auth UserAuthDetails authDetails) throws ControllerException;

    @PutMapping("{id}")
    E update(@RequestBody E entity, @PathVariable("id") ID id, @Auth UserAuthDetails authDetails) throws ControllerException;

    @DeleteMapping("{id}")
    void delete(@PathVariable("id") ID id, @Auth UserAuthDetails authDetails) throws ControllerException;

}
