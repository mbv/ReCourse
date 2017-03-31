package by.triumgroup.recourse.controller;

import by.triumgroup.recourse.controller.exception.ControllerException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public interface CrudController<E, ID> {

    @GetMapping("{id}")
    E getById(@PathVariable("id") ID id) throws ControllerException;

    @PutMapping("update")
    @PostMapping("add")
    <S extends E> S save(@Valid @RequestBody S entity) throws ControllerException;

    @DeleteMapping("{id}")
    void delete(@PathVariable("id") ID id) throws ControllerException;

}
