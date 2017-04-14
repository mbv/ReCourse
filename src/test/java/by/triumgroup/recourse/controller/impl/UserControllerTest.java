package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.CrudController;
import by.triumgroup.recourse.controller.CrudControllerTest;
import by.triumgroup.recourse.controller.UserController;
import by.triumgroup.recourse.controller.impl.UserControllerImpl;
import by.triumgroup.recourse.entity.dto.RegistrationDetails;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.service.UserService;
import by.triumgroup.recourse.supplier.entity.dto.RegistrationDetailsSupplier;
import by.triumgroup.recourse.supplier.entity.model.EntitySupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.UserSupplier;
import by.triumgroup.recourse.validation.RegistrationDetailsValidator;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.validation.Errors;

import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class UserControllerTest extends CrudControllerTest<User, Integer> {

    private RegistrationDetailsSupplier registrationDetailsSupplier;

    private UserService userService;

    private RegistrationDetailsValidator registrationDetailsValidator;

    private UserController userController;

    private EntitySupplier<User, Integer> entitySupplier;

    public UserControllerTest() {
        userService = Mockito.mock(UserService.class);
        registrationDetailsValidator = Mockito.mock(RegistrationDetailsValidator.class);
        when(registrationDetailsValidator.supports(RegistrationDetails.class)).thenReturn(true);
        userController = new UserControllerImpl(userService, registrationDetailsValidator);
        entitySupplier = new UserSupplier();
        registrationDetailsSupplier = new RegistrationDetailsSupplier();
    }

    @Test
    public void registrationWithSuccessfulValidationTest() throws Exception {
        RegistrationDetails registrationDetails = registrationDetailsSupplier.get();
        when(userService.register(any())).thenReturn(Optional.of(true));

        sendPost("/user/register", registrationDetails)
                .andExpect(status().isOk());

        verify(registrationDetailsValidator, times(1)).validate(any(), any());
    }


    @Test
    public void registrationWithFailedValidationTest() throws Exception {
        RegistrationDetails registrationDetails = registrationDetailsSupplier.get();
        doAnswer(invocationOnMock -> {
            Errors errors = (Errors)invocationOnMock.getArguments()[1];
            errors.rejectValue("", "");
            return null;
        }).when(registrationDetailsValidator).validate(any(), any());
        when(userService.register(any())).thenReturn(Optional.of(true));

        sendPost("/user/register", registrationDetails)
                .andExpect(status().isBadRequest());

        verify(registrationDetailsValidator, times(1)).validate(any(), any());
    }

    @Override
    protected String getEntityName() {
        return "user";
    }

    @Override
    protected CrudController<User, Integer> getController() {
        return userController;
    }

    @Override
    protected CrudService<User, Integer> getService() {
        return userService;
    }

    @Override
    protected EntitySupplier<User, Integer> getEntitySupplier() {
        return entitySupplier;
    }
}
