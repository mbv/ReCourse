package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.CrudController;
import by.triumgroup.recourse.controller.CrudControllerTest;
import by.triumgroup.recourse.controller.UserController;
import by.triumgroup.recourse.entity.dto.RegistrationDetails;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.service.UserService;
import by.triumgroup.recourse.service.exception.ServiceBadRequestException;
import by.triumgroup.recourse.supplier.entity.dto.RegistrationDetailsSupplier;
import by.triumgroup.recourse.supplier.entity.model.EntitySupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.UserSupplier;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.util.Optional;

import static by.triumgroup.recourse.util.Util.allItemsPage;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class UserControllerTest extends CrudControllerTest<User, Integer> {

    private static final String USER_REGISTER_REQUEST = "/api/users/register";
    private RegistrationDetailsSupplier registrationDetailsSupplier;

    private UserService userService;

    private UserController userController;

    private EntitySupplier<User, Integer> entitySupplier;

    public UserControllerTest() {
        userService = Mockito.mock(UserService.class);
        userController = new UserControllerImpl(userService, null);
        entitySupplier = new UserSupplier();
        registrationDetailsSupplier = new RegistrationDetailsSupplier();
    }

    @Test
    public void registrationWithSuccessfulValidationTest() throws Exception {
        RegistrationDetails registrationDetails = registrationDetailsSupplier.get();
        when(userService.register(any())).thenReturn(Optional.of(true));

        sendPost(USER_REGISTER_REQUEST, registrationDetails)
                .andExpect(status().isOk());

        verify(userService, times(1)).register(any());
    }


    @Test
    public void registrationWithFailedValidationTest() throws Exception {
        RegistrationDetails registrationDetails = registrationDetailsSupplier.get();
        BindingResult bindingResult = new BeanPropertyBindingResult(registrationDetails, "registration details");
        when(userService.register(any())).thenThrow(new ServiceBadRequestException(bindingResult));

        sendPost(USER_REGISTER_REQUEST, registrationDetails)
                .andExpect(status().isBadRequest());

        verify(userService, times(1)).register(any());
    }

    @Override
    public void createValidEntityTest() throws Exception {
        postEntityAuthorized(getEntitySupplier().getValidEntityWithoutId())
                .andExpect(status().isMethodNotAllowed());
    }

    @Override
    public void createInvalidEntityTest() throws Exception {
        postEntityAuthorized(getEntitySupplier().getValidEntityWithoutId())
                .andExpect(status().isMethodNotAllowed());
    }

    @Override
    public void deleteExistingEntityTest() throws Exception {
        sendDelete(idRequest, userSupplier.getWithRole(User.Role.ADMIN), entitySupplier.getAnyId()).
                andExpect(status().isMethodNotAllowed());
    }

    @Override
    public void deleteNotExistingEntityTest() throws Exception {
        sendDelete(idRequest, userSupplier.getWithRole(User.Role.ADMIN), entitySupplier.getAnyId()).
                andExpect(status().isMethodNotAllowed());
    }

    @Override
    public void updateNotExistingEntityTest() throws Exception {
        when(userService.update(any(), any(), any())).thenReturn(Optional.empty());

        putEntityByIdAuthorized(getEntitySupplier().getAnyId(), getEntitySupplier().getValidEntityWithoutId())
                .andExpect(status().isNotFound());
    }

    @Override
    public void updateEntityValidDataTest() throws Exception {
        when(userService.update(any(), any(), any())).thenReturn(Optional.of(getEntitySupplier().getValidEntityWithId()));

        putEntityByIdAuthorized(getEntitySupplier().getAnyId(), getEntitySupplier().getValidEntityWithoutId())
                .andExpect(status().isOk());
    }

    @Override
    public void getAllEntitiesTest() throws Exception {
        when(getService().findAll(allItemsPage())).thenReturn(Lists.emptyList());
        User admin = userSupplier.getWithRole(User.Role.ADMIN);
        sendGet(generalRequest, admin)
                .andExpect(status().isOk());
    }

    @Override
    public void updateEntityInvalidDataTest() throws Exception {
        super.updateEntityInvalidDataTest();
    }

    @Override
    protected String getEntityName() {
        return "users";
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

    @Override
    protected User prepareAuthorizedUser(User entity, User validUserWithId) {
        validUserWithId.setRole(User.Role.ADMIN);
        return validUserWithId;
    }
}
