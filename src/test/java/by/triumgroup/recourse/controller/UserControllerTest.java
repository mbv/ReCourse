package by.triumgroup.recourse.controller;

import by.triumgroup.recourse.controller.impl.UserControllerImpl;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.service.UserService;
import by.triumgroup.recourse.supplier.bean.DefaultTestBeansSupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.UserSupplier;


public class UserControllerTest extends CrudControllerTest<User, Integer, UserController, UserService> {

//    private RegistrationDetailsSupplier registrationDetailsSupplier = new RegistrationDetailsSupplier();

    public UserControllerTest() {
        super(new UserSupplier(), new DefaultTestBeansSupplier<>(UserControllerImpl.class, UserService.class), "user");
    }
//
//    @Test
//    public void registrationWithExistingEmailTest() throws Exception {
//
//        mockMvc
//    }
}
