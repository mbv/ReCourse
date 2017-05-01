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
import by.triumgroup.recourse.service.exception.ServiceBadRequestException;
import by.triumgroup.recourse.service.exception.ServiceException;
import by.triumgroup.recourse.validation.validator.PasswordChangingValidator;
import by.triumgroup.recourse.validation.validator.RegistrationDetailsValidator;
import org.slf4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import static by.triumgroup.recourse.util.RepositoryCallWrapper.*;
import static by.triumgroup.recourse.util.Util.allItemsPage;
import static org.slf4j.LoggerFactory.getLogger;


@Component
public class UserServiceImpl extends AbstractCrudService<User, Integer> implements UserService {

    private static final Logger logger = getLogger(UserServiceImpl.class);

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
    public List<User> findByRole(User.Role role, Pageable pageable) {
        return wrapJPACall(() -> userRepository.findByRole(role, pageable));
    }

    @Override
    public Optional<User> findByEmail(String email) throws ServiceException {
        return wrapJPACallToOptional(() -> userRepository.findByEmail(email));
    }

    @Override
    public Optional<User> update(User entity, Integer id) {
        logger.warn("This method shouldn't be called.");
        throw new ServiceException();
    }

    @Override
    public <S extends User> Optional<S> update(S newUser, Integer id, User performer) throws ServiceException {
        Optional<S> result;
        Integer performerId = performer.getId();
        performer = wrapJPACall(() -> userRepository.findOne(performerId));
        Optional<User> databaseUserOptional = wrapJPACallToOptional(() -> userRepository.findOne(id));
        if (databaseUserOptional.isPresent()) {
            User databaseUser = databaseUserOptional.get();
            restoreUserPermanentValues(databaseUser, newUser);
            if (databaseUser.getRole() != newUser.getRole()) {
                if (performer.getRole() == User.Role.ADMIN) {
                    if (!performer.getId().equals(databaseUser.getId())) {
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
                    checkTeacherRoleUpdate(databaseUser);
                    break;
                case STUDENT:
                    checkStudentRoleUpdate(databaseUser);
                    break;
                case ADMIN:
                    break;
                case DISABLED:
                    checkUserEnabling(databaseUser, newUser.getRole());
                    break;
                default:
                    rejectRoleChanging("Unknown role");
            }
        }
        forceLogoutUser(databaseUser);
    }

    private void checkUserEnabling(User disabledUser, User.Role newRole) {
        switch (newRole) {
            case TEACHER:
                if (!disabledUser.getCourses().isEmpty()) {
                    rejectRoleChanging("Teacher can't be registered to courses.");
                }
                break;
            case STUDENT:
                List<Lesson> lessons = wrapJPACall(() -> lessonRepository.findByTeacherIdOrderByStartTimeAsc(disabledUser.getId(), allItemsPage()));
                if (!lessons.isEmpty()) {
                    rejectRoleChanging("Teacher can't have any lessons.");
                }
                break;
            case ADMIN:
                lessons = wrapJPACall(() -> lessonRepository.findByTeacherIdOrderByStartTimeAsc(disabledUser.getId(), allItemsPage()));
                if (!lessons.isEmpty() || !disabledUser.getCourses().isEmpty()) {
                    rejectRoleChanging("Admin can't have any lessons or be registered to courses.");
                }
                break;
            default:
                rejectRoleChanging("Unknown role");
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
                List<Lesson> lessons = wrapJPACall(() -> lessonRepository.findByTeacherIdOrderByStartTimeAsc(
                        newUser.getId(), allItemsPage()));
                if (lessons.stream().anyMatch(
                        lesson -> lesson.getStartTime().after(Timestamp.from(Instant.now())))) {
                    rejectRoleChanging("Teacher has lessons in the future.");
                }
                break;
        }
    }

    private void checkTeacherRoleUpdate(User teacher) {
        List<Lesson> lessons = wrapJPACall(() -> lessonRepository.findByTeacherIdOrderByStartTimeAsc(
                teacher.getId(), allItemsPage()));
        if (!lessons.isEmpty()) {
            rejectRoleChanging("Teacher has lessons.");
        }
    }

    private void checkStudentRoleUpdate(User student) {
        if (!student.getCourses().isEmpty()) {
            rejectRoleChanging("Student is registered to courses.");
        }
    }

    private void rejectRoleChanging(String message) {
        throw new ServiceBadRequestException("role", message);
    }

    private void denyRoleChanging(String message) {
        throw new ServiceBadRequestException(new ErrorMessage("role", message));
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
        newUser.setRole(User.Role.STUDENT);

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