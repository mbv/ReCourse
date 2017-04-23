package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.dto.RegistrationDetails;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.service.UserService;
import by.triumgroup.recourse.service.exception.ServiceException;
import by.triumgroup.recourse.validation.validator.RegistrationDetailsValidator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static by.triumgroup.recourse.util.RepositoryCallWrapper.wrapJPACallToBoolean;
import static by.triumgroup.recourse.util.RepositoryCallWrapper.wrapJPACallToOptional;

@Component
public class UserServiceImpl extends AbstractCrudService<User, Integer> implements UserService {

    private UserRepository userRepository;
    private RegistrationDetailsValidator registrationDetailsValidator;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RegistrationDetailsValidator registrationDetailsValidator) {
        super(userRepository);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.registrationDetailsValidator = registrationDetailsValidator;
    }

    @Override
    public Optional<User> findByEmail(String email) throws ServiceException {
        return wrapJPACallToOptional(() -> userRepository.findByEmail(email));
    }

    @Override
    public <S extends User> Optional<S> update(S entity, Integer id) throws ServiceException {
        Optional<User> updatingUser = wrapJPACallToOptional(() -> userRepository.findOne(id));
        if (updatingUser.isPresent()){
            User existingUser = updatingUser.get();
            entity.setPasswordHash(existingUser.getPasswordHash());
        }
        return super.update(entity, id);
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
    protected List<Validator> getValidators() {
        return Collections.singletonList(registrationDetailsValidator);
    }

    @Override
    protected String getEntityName() {
        return "user";
    }

}