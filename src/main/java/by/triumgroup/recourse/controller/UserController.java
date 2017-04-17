package by.triumgroup.recourse.controller;

import by.triumgroup.recourse.controller.exception.ControllerException;
import by.triumgroup.recourse.entity.dto.RegistrationDetails;
import by.triumgroup.recourse.entity.model.User;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public interface UserController extends CrudController<User, Integer> {
    @PostMapping("register")
    void register(@RequestBody RegistrationDetails registrationDetails) throws ControllerException;

    @PostMapping("/logout")
    void logout(OAuth2Authentication principal);
}
