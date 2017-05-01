package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.dto.RegistrationDetails;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.repository.LessonRepository;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.service.CrudServiceTest;
import by.triumgroup.recourse.service.UserService;
import by.triumgroup.recourse.service.exception.ServiceBadRequestException;
import by.triumgroup.recourse.service.exception.ServiceException;
import by.triumgroup.recourse.supplier.entity.dto.RegistrationDetailsSupplier;
import by.triumgroup.recourse.supplier.entity.model.EntitySupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.UserSupplier;
import by.triumgroup.recourse.validation.validator.PasswordChangingValidator;
import by.triumgroup.recourse.validation.validator.RegistrationDetailsValidator;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Pair;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.validation.Errors;

import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class UserServiceTest extends CrudServiceTest<User, Integer> {

    private RegistrationDetailsSupplier registrationDetailsSupplier;

    private UserService userService;

    private UserRepository userRepository;

    private LessonRepository lessonRepository;

    private TokenStore tokenStore;

    private ConsumerTokenServices consumerTokenServices;

    private PasswordEncoder passwordEncoder;

    private UserSupplier userSupplier;

    private RegistrationDetailsValidator registrationDetailsValidator;

    public UserServiceTest() {
        registrationDetailsSupplier = new RegistrationDetailsSupplier();
        userRepository = Mockito.mock(UserRepository.class);
        lessonRepository = Mockito.mock(LessonRepository.class);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        tokenStore = Mockito.mock(TokenStore.class);
        consumerTokenServices = Mockito.mock(ConsumerTokenServices.class);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        registrationDetailsValidator = Mockito.mock(RegistrationDetailsValidator.class);

        when(registrationDetailsValidator.supports(any())).thenCallRealMethod();
        PasswordChangingValidator passwordChangingValidator = Mockito.mock(PasswordChangingValidator.class);
        userService = new UserServiceImpl(userRepository,
                lessonRepository,
                passwordEncoder,
                registrationDetailsValidator,
                tokenStore,
                consumerTokenServices,
                passwordChangingValidator);
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
    public void registrationFailedTest() throws Exception {
        RegistrationDetails registrationDetails = registrationDetailsSupplier.get();
        doAnswer(invocationOnMock -> {
            Errors errors = (Errors)invocationOnMock.getArguments()[1];
            errors.rejectValue("", "");
            return null;
        }).when(registrationDetailsValidator).validate(any(), any());
        when(userRepository.save(Matchers.<User>any())).thenReturn(userSupplier.getValidEntityWithId());

        thrown.expect(ServiceBadRequestException.class);

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

    @Test
    @Override
    public void updateEntityWithoutIdTest() throws Exception {
        User newEntity = getEntitySupplier().getValidEntityWithoutId();
        User databaseEntity = getEntitySupplier().getValidEntityWithoutId();
        Integer parameterId = getEntitySupplier().getAnyId();
        databaseEntity.setId(parameterId);
        when(getCrudRepository().save(newEntity)).thenReturn(newEntity);
        when(getCrudRepository().exists(parameterId)).thenReturn(true);
        when(getCrudRepository().findOne(parameterId)).thenReturn(databaseEntity);
        setupAllowedRoles(newEntity);

        Optional<User> actualResult = userService.update(newEntity, parameterId, userSupplier.getWithRole(User.Role.ADMIN));

        verify(getCrudRepository()).save(captor.capture());
        verify(getCrudRepository(), times(1)).save(Matchers.<User>any());
        Assert.assertEquals(newEntity, actualResult.orElse(null));
        Assert.assertEquals(parameterId, captor.getValue().getId());
    }

    @Test
    @Override
    public void updateEntityWithDifferentParameterIdTest() throws Exception {
        Pair<Integer, Integer> ids = getEntitySupplier().getDifferentIds();
        Integer entityId = ids.getFirst();
        Integer parameterId = ids.getSecond();
        User newEntity = getEntitySupplier().getValidEntityWithoutId();
        User databaseEntity = getEntitySupplier().getValidEntityWithoutId();
        databaseEntity.setId(parameterId);
        newEntity.setId(entityId);
        when(getCrudRepository().save(newEntity)).thenReturn(newEntity);
        when(getCrudRepository().exists(parameterId)).thenReturn(true);
        when(getCrudRepository().findOne(parameterId)).thenReturn(databaseEntity);
        setupAllowedRoles(newEntity);

        Optional<User> actualResult = userService.update(newEntity, parameterId, userSupplier.getWithRole(User.Role.ADMIN));

        verify(getCrudRepository()).save(captor.capture());
        verify(getCrudRepository(), times(1)).save(Matchers.<User>any());
        Assert.assertEquals(newEntity, actualResult.orElse(null));
        Assert.assertEquals(parameterId, captor.getValue().getId());
    }

    @Test
    @Override
    public void updateNotExistingEntityTest() throws Exception {
        User entity = getEntitySupplier().getValidEntityWithoutId();
        Integer parameterId = getEntitySupplier().getAnyId();
        when(getCrudRepository().exists(parameterId)).thenReturn(false);
        when(getCrudRepository().findOne(parameterId)).thenReturn(null);

        Optional<User> actualResult = userService.update(entity, parameterId, userSupplier.getWithRole(User.Role.ADMIN));

        verify(getCrudRepository(), times(0)).save(entity);
        Assert.assertFalse(actualResult.isPresent());
    }

    @Test
    @Override
    public void updateEntityExceptionTest() throws Exception {
        User entity = getEntitySupplier().getValidEntityWithoutId();
        Integer parameterId = getEntitySupplier().getAnyId();
        when(getCrudRepository().save(Matchers.<User>any())).thenThrow(new DataIntegrityViolationException(""));
        when(getCrudRepository().exists(any())).thenReturn(true);
        when(getCrudRepository().findOne(parameterId)).thenReturn(entity);
        setupAllowedRoles(entity);

        thrown.expect(ServiceException.class);

        userService.update(entity, parameterId, userSupplier.getWithRole(User.Role.ADMIN));

        verify(getCrudRepository(), times(1)).save(Matchers.<User>any());
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

    @Override
    protected void setupAllowedRoles(User entity) { }
}

