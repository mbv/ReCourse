package by.triumgroup.recourse.service;

import by.triumgroup.recourse.entity.dto.RegistrationDetails;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.supplier.bean.impl.UserServiceTestBeansSupplier;
import by.triumgroup.recourse.supplier.entity.dto.RegistrationDetailsSupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.UserSupplier;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import static by.triumgroup.recourse.util.TestUtil.createValidationErrors;
import static org.mockito.Mockito.when;

public class UserServiceTest extends CrudServiceTest<User, Integer, UserService, UserRepository> {

    private RegistrationDetailsSupplier registrationDetailsSupplier = new RegistrationDetailsSupplier();

    private UserService userService = testBeansSupplier.getBeanUnderTest();
    private UserRepository userRepository = testBeansSupplier.getMockedBean();


    public UserServiceTest() {
        super(new UserServiceTestBeansSupplier(), new UserSupplier());
    }

    @Test
    public void registrationWithExistingEmailTest() throws Exception {
        RegistrationDetails registrationDetails = registrationDetailsSupplier.get();
        when(userRepository.findByEmail(registrationDetails.getEmail())).thenReturn(entitySupplier.getValidEntityWithId());
        Errors errors = createValidationErrors(registrationDetails, "registrationDetails");

        userService.register(registrationDetails, errors);

        Assert.assertTrue(errors.hasFieldErrors());
        Assert.assertTrue(errors.getErrorCount() == 1);
        FieldError fieldError = errors.getFieldError();
        Assert.assertEquals("email", fieldError.getField());
    }

    @Test
    public void registrationWithDifferentPasswordsTest() throws Exception {
        RegistrationDetails registrationDetails = registrationDetailsSupplier.get();
        Errors errors = createValidationErrors(registrationDetails, "registrationDetails");
        String password = registrationDetails.getPassword();
        String passwordConfirmation = String.format("%s %s", password, password);
        registrationDetails.setPasswordConfirmation(passwordConfirmation);

        userService.register(registrationDetails, errors);

        Assert.assertTrue(errors.hasFieldErrors());
        Assert.assertTrue(errors.getErrorCount() == 1);
        FieldError fieldError = errors.getFieldError();
        Assert.assertEquals("password", fieldError.getField());
    }
}

