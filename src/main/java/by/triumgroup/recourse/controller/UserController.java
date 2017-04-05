package by.triumgroup.recourse.controller;

import by.triumgroup.recourse.entity.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public interface UserController extends CrudController<User, Integer> {
}
