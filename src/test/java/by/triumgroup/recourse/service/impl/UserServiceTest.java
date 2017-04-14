package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.dto.RegistrationDetails;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.service.CrudServiceTest;
import by.triumgroup.recourse.service.UserService;
import by.triumgroup.recourse.service.exception.ServiceException;
import by.triumgroup.recourse.supplier.entity.dto.RegistrationDetailsSupplier;
import by.triumgroup.recourse.supplier.entity.model.EntitySupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.UserSupplier;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class UserServiceTest extends CrudServiceTest<User, Integer> {

    private RegistrationDetailsSupplier registrationDetailsSupplier;

    private UserService userService;

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private UserSupplier userSupplier;

    public UserServiceTest() {
        registrationDetailsSupplier = new RegistrationDetailsSupplier();
        userRepository = Mockito.mock(UserRepository.class);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        userService = new UserServiceImpl(userRepository, passwordEncoder);
        userSupplier = new UserSupplier();
    }

    @Test
    public void registrationSuccessTest() throws Exception {
        RegistrationDetails registrationDetails = registrationDetailsSupplier.get();
        when(userRepository.save(Matchers.<User>any())).thenReturn(userSupplier.getValidEntityWithId());

        Optional<Boolean> result = userService.register(registrationDetails);

        verify(userRepository, times(1)).save(Matchers.<User>any());
        verify(passwordEncoder, times(1)).encode(registrationDetails.getPassword());
        Assert.assertTrue(result.isPresent());
    }

    @Test
    public void registrationExceptionTest() throws Exception {
        RegistrationDetails registrationDetails = registrationDetailsSupplier.get();
        when(userRepository.save(Matchers.<User>any())).thenThrow(new DataIntegrityViolationException(""));

        thrown.expect(ServiceException.class);

        userService.register(registrationDetails);
    }

    @Test
    public void findByExistingEmailTest() throws Exception {
        User user = userSupplier.getValidEntityWithId();
        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);

        Optional<User> result = userService.findByEmail(user.getEmail());

        verify(userRepository, times(1)).findByEmail(user.getEmail());
        Assert.assertEquals(user, result.orElse(null));
    }

    @Test
    public void findByNotExistingEmailTest() throws Exception {
        User user = userSupplier.getValidEntityWithId();
        when(userRepository.findByEmail(user.getEmail())).thenReturn(null);

        Optional<User> result = userService.findByEmail(user.getEmail());

        verify(userRepository, times(1)).findByEmail(user.getEmail());
        Assert.assertFalse(result.isPresent());
    }


    @Override
    protected CrudService<User, Integer> getCrudService() {
        return userService;
    }

    @Override
    protected CrudRepository<User, Integer> getCrudRepository() {
        return userRepository;
    }

    @Override
    protected EntitySupplier<User, Integer> getEntitySupplier() {
        return userSupplier;
    }
}

