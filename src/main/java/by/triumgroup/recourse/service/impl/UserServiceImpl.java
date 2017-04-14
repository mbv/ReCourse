package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.dto.RegistrationDetails;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.service.UserService;
import by.triumgroup.recourse.service.exception.ServiceException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static by.triumgroup.recourse.util.RepositoryCallWrapper.wrapJPACallToBoolean;
import static by.triumgroup.recourse.util.RepositoryCallWrapper.wrapJPACallToOptional;

@Component
public class UserServiceImpl extends AbstractCrudService<User, Integer> implements UserService {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        super(userRepository);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> findByEmail(String email) throws ServiceException {
        return wrapJPACallToOptional(() -> userRepository.findByEmail(email));
    }

    @Override
    public <S extends User> Optional<S> update(S entity, Integer integer) throws ServiceException {
        Optional<User> updatingUser = wrapJPACallToOptional(() -> userRepository.findOne(integer));
        if (updatingUser.isPresent()){
            User existingUser = updatingUser.get();
            entity.setPasswordHash(existingUser.getPasswordHash());
        }
        return super.update(entity, integer);
    }

    @Override
    public Optional<Boolean> register(RegistrationDetails registrationDetails) throws ServiceException {
        User newUser = new User();
        newUser.setEmail(registrationDetails.getEmail());
        newUser.setName(registrationDetails.getName());
        newUser.setSurname(registrationDetails.getSurname());
        newUser.setBirthday(registrationDetails.getBirthday());
        newUser.setGender(registrationDetails.getGender());
        newUser.setPasswordHash(passwordEncoder.encode(registrationDetails.getPassword()));

        return wrapJPACallToBoolean(() -> userRepository.save(newUser));
    }
}