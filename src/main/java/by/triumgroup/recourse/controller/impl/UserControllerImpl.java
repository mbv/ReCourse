package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.UserController;
import by.triumgroup.recourse.entity.User;
import by.triumgroup.recourse.service.UserService;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class UserControllerImpl extends AbstractCrudController<User, Integer> implements UserController {

    private static final Logger logger = getLogger(UserControllerImpl.class);

    public UserControllerImpl(UserService userService) {
        super(userService, logger);
    }
}
