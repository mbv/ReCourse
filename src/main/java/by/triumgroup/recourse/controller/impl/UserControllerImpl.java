package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.UserController;
import by.triumgroup.recourse.controller.exception.ControllerException;
import by.triumgroup.recourse.entity.User;
import by.triumgroup.recourse.service.UserService;
import org.slf4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;

import static org.slf4j.LoggerFactory.getLogger;

public class UserControllerImpl extends AbstractCrudController<User, Long> implements UserController {

    private static final Logger logger = getLogger(UserControllerImpl.class);

    public UserControllerImpl(UserService userService) {
        super(userService, logger);
    }

    @Override
    @PreAuthorize("hasAuthority('GUEST')")
    public User getById(@PathVariable("id") Long id) throws ControllerException {
        return super.getById(id);
    }
}
