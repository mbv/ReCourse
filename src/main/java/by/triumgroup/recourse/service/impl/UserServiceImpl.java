package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.dto.ErrorMessage;
import by.triumgroup.recourse.entity.dto.PasswordChanging;
import by.triumgroup.recourse.entity.dto.RegistrationDetails;
import by.triumgroup.recourse.entity.model.Course;
import by.triumgroup.recourse.entity.model.Lesson;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.repository.LessonRepository;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.service.UserService;
import by.triumgroup.recourse.service.exception.ServiceException;
import by.triumgroup.recourse.validation.exception.ServiceAccessDeniedException;
import by.triumgroup.recourse.validation.exception.ServiceBadRequestException;
import by.triumgroup.recourse.validation.validator.PasswordChangingValidator;
import by.triumgroup.recourse.validation.validator.RegistrationDetailsValidator;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import static by.triumgroup.recourse.util.RepositoryCallWrapper.*;


@Component
public class UserServiceImpl extends AbstractCrudService<User, Integer> implements UserService {

    private final LessonRepository lessonRepository;
    private final TokenStore tokenStore;
    private final ConsumerTokenServices consumerTokenServices;
    private UserRepository userRepository;
    private RegistrationDetailsValidator registrationDetailsValidator;
    private PasswordChangingValidator passwordChangingValidator;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           LessonRepository lessonRepository,
                           PasswordEncoder passwordEncoder,
                           RegistrationDetailsValidator registrationDetailsValidator,
                           TokenStore tokenStore,
                           ConsumerTokenServices consumerTokenServices,
                           PasswordChangingValidator passwordChangingValidator) {
        super(userRepository);
        this.userRepository = userRepository;
        this.lessonRepository = lessonRepository;
        this.passwordEncoder = passwordEncoder;
        this.registrationDetailsValidator = registrationDetailsValidator;
        this.tokenStore = tokenStore;
        this.consumerTokenServices = consumerTokenServices;
        this.passwordChangingValidator = passwordChangingValidator;
    }

    @Override
    public Optional<User> findByEmail(String email) throws ServiceException {
        return wrapJPACallToOptional(() -> userRepository.findByEmail(email));
    }

    @Override
    public <S extends User> Optional<S> update(S entity, Integer id) {
        throw new NotImplementedException();
    }

    @Override
    public <S extends User> Optional<S> update(S newUser, Integer id, User performer) throws ServiceException {
        Optional<S> result;
        Optional<User> databaseUserOptional = wrapJPACallToOptional(() -> userRepository.findOne(id));
        if (databaseUserOptional.isPresent()) {
            User databaseUser = databaseUserOptional.get();
            restoreUserPermanentValues(databaseUser, newUser);
            if (databaseUser.getRole() != newUser.getRole()) {
                if (performer.getRole() == User.Role.ADMIN) {
                    if (!performer.getId().equals(databaseUser.getId())){
                        handleRoleUpdating(databaseUser, newUser);
                    } else {
                        rejectRoleChanging("Admin can not downgrade himself");
                    }
                } else {
                    denyRoleChanging("Role changing is denied.");
                }
            }
            result = wrapJPACallToOptional(() -> userRepository.save(newUser));
        } else {
            result = Optional.empty();
        }
        return result;
    }

    private void restoreUserPermanentValues(User databaseUser, User newUser) {
        newUser.setId(databaseUser.getId());
        newUser.setPasswordHash(databaseUser.getPasswordHash());
    }

    private void handleRoleUpdating(User databaseUser, User newUser) {
        if (newUser.getRole() == User.Role.DISABLED) {
            disableUser(databaseUser, newUser);
        } else {
            switch (databaseUser.getRole()) {
                case TEACHER:
                    checkTeacherRoleUpdate(newUser);
                    break;
                case STUDENT:
                    checkStudentRoleUpdate(newUser);
                    break;
                default:
                    rejectRoleChanging("Unknown role");
            }
        }
    }

    private void disableUser(User databaseUser, User newUser) {
        switch (databaseUser.getRole()) {
            case STUDENT:
                Set<Course> courses = databaseUser.getCourses();
                courses = courses.stream()
                        .filter(course -> course.getStatus() == Course.Status.FINISHED)
                        .collect(Collectors.toSet());
                newUser.setCourses(courses);
                break;
            case TEACHER:
                List<Lesson> lessons = wrapJPACall(() -> lessonRepository.findByTeacherIdOrderByStartTimeDesc(
                        newUser.getId(), new PageRequest(0, Integer.MAX_VALUE)));
                if (lessons.stream().anyMatch(
                        lesson -> lesson.getStartTime().after(Timestamp.from(Instant.now())))) {
                    rejectRoleChanging("Teacher has lessons in the future.");
                }
                break;
        }
        forceLogoutUser(databaseUser);
    }

    private void checkTeacherRoleUpdate(User teacher) {
        List<Lesson> lessons = wrapJPACall(() -> lessonRepository.findByTeacherIdOrderByStartTimeDesc(
                teacher.getId(), new PageRequest(0, Integer.MAX_VALUE)));
        if (!lessons.isEmpty()){
            rejectRoleChanging("Teacher has lessons.");
        }
    }

    private void checkStudentRoleUpdate(User student) {
        if (!student.getCourses().isEmpty()){
            rejectRoleChanging("Student is registered to courses.");
        }
    }

    private void rejectRoleChanging(String message) {
        throw new ServiceBadRequestException(new ErrorMessage("role", message));
    }

    private void denyRoleChanging(String message) {
        throw new ServiceAccessDeniedException(new ErrorMessage("role", message));
    }

    private void forceLogoutUser(User user) {
        Collection<OAuth2AccessToken> tokens = tokenStore.findTokensByClientIdAndUserName(
                "web_client",
                user.getEmail());
        for (OAuth2AccessToken token : tokens) {
            consumerTokenServices.revokeToken(token.getValue());
        }
    }

    @Override
    public Optional<Boolean> register(RegistrationDetails registrationDetails) throws ServiceException {
        validate(registrationDetails, "registration details");
        User newUser = new User();
        newUser.setEmail(registrationDetails.getEmail());
        newUser.setName(registrationDetails.getName());
        newUser.setSurname(registrationDetails.getSurname());
        newUser.setBirthday(registrationDetails.getBirthday());
        newUser.setGender(registrationDetails.getGender());
        newUser.setPasswordHash(passwordEncoder.encode(registrationDetails.getPassword()));

        return wrapJPACallToBoolean(() -> userRepository.save(newUser));
    }

    @Override
    public Optional<Boolean> changePassword(Integer userId, PasswordChanging passwordChanging) throws ServiceException {
        Optional<Boolean> result;
        validate(passwordChanging, "password changing");
        Optional<User> databaseUserOptional = wrapJPACallToOptional(() -> userRepository.findOne(userId));
        if (databaseUserOptional.isPresent()) {
            User databaseUser = databaseUserOptional.get();
            String newPasswordHash = passwordEncoder.encode(passwordChanging.getPassword());
            databaseUser.setPasswordHash(newPasswordHash);
            wrapJPACall(() -> userRepository.save(databaseUser));
            result = Optional.of(true);
        } else {
            result = Optional.empty();
        }
        return result;
    }

    @Override
    protected List<Validator> getValidators() {
        return Arrays.asList(registrationDetailsValidator, passwordChangingValidator);
    }

    @Override
    protected String getEntityName() {
        return "user";
    }

}