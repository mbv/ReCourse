package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.UserController;
import by.triumgroup.recourse.controller.exception.BadRequestException;
import by.triumgroup.recourse.controller.exception.ControllerException;
import by.triumgroup.recourse.entity.dto.RegistrationDetails;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.service.UserService;
import by.triumgroup.recourse.service.exception.ServiceException;
import by.triumgroup.recourse.validation.RegistrationDetailsValidator;
import org.slf4j.Logger;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

import static org.slf4j.LoggerFactory.getLogger;

public class UserControllerImpl extends AbstractCrudController<User, Integer> implements UserController {

    private static final Logger logger = getLogger(UserControllerImpl.class);
    private UserService userService;
    private RegistrationDetailsValidator registrationDetailsValidator;

    public UserControllerImpl(UserService userService, RegistrationDetailsValidator registrationDetailsValidator) {
        super(userService, logger);
        this.userService = userService;
        this.registrationDetailsValidator = registrationDetailsValidator;
    }

    @InitBinder("registrationDetails")
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(registrationDetailsValidator);
    }

    @Override
    public void register(@Valid @RequestBody RegistrationDetails registrationDetails) throws ControllerException {
        try {
            userService.register(registrationDetails).orElseThrow(BadRequestException::new);
        } catch (ServiceException e){
            logger.warn("Error while user registration");
            throw new ControllerException(e);
        }
    }
}