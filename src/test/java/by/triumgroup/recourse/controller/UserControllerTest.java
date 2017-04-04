package by.triumgroup.recourse.controller;

import by.triumgroup.recourse.controller.impl.UserControllerImpl;
import by.triumgroup.recourse.entity.User;
import by.triumgroup.recourse.supplier.entity.impl.UserSupplier;
import by.triumgroup.recourse.service.UserService;
import by.triumgroup.recourse.supplier.bean.TestBeansSupplier;


public class UserControllerTest extends CrudControllerTest<User, Integer, UserController, UserService> {

    public UserControllerTest() {
        super(new UserSupplier(), new TestBeansSupplier<>(UserControllerImpl.class, UserService.class), "user");
    }
}
