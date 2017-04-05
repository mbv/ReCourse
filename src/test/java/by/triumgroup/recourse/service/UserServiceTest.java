package by.triumgroup.recourse.service;

import by.triumgroup.recourse.entity.dto.RegistrationDetails;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.supplier.bean.impl.UserServiceTestBeansSupplier;
import by.triumgroup.recourse.supplier.entity.dto.impl.RegistrationDetailsSupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.UserSupplier;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.mockito.Mockito.when;

public class UserServiceTest extends CrudServiceTest<User, Integer, UserService, UserRepository> {

    private RegistrationDetailsSupplier registrationDetailsSupplier = new RegistrationDetailsSupplier();

    private UserService userService = testBeansSupplier.getBeanUnderTest();
    private UserRepository userRepository = testBeansSupplier.getMockedBean();

    public UserServiceTest() {
        super(new UserServiceTestBeansSupplier(), new UserSupplier());
    }

    @Test
    public void registrationTest() throws Exception {
        RegistrationDetails registrationDetails = registrationDetailsSupplier.getValidDto();
        when(userRepository.findByEmail(registrationDetails.getEmail())).thenReturn(entitySupplier.getValidEntityWithId());
        Errors errors = new BeanPropertyBindingResult(registrationDetails, "registrationDetails");
        userService.register(registrationDetails, errors);
        Assert.assertTrue(errors.hasErrors());
    }
}

