package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.dto.RegistrationDetails;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.service.UserService;
import by.triumgroup.recourse.service.exception.ServiceException;
import by.triumgroup.recourse.validation.RegistrationDetailsValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import javax.validation.Valid;
import java.util.Optional;

import static by.triumgroup.recourse.service.exception.wrapper.ServiceExceptionWrapper.tryCallJPA;
import static by.triumgroup.recourse.service.util.RepositoryCallWrapper.wrapToOptional;

@Component
public class UserServiceImpl extends AbstractCrudService<User, Integer> implements UserService {

    private final UserRepository userRepository;

    private RegistrationDetailsValidator registrationDetailsValidator;

    public UserServiceImpl(UserRepository userRepository, RegistrationDetailsValidator registrationDetailsValidator) {
        super(userRepository);
        this.userRepository = userRepository;
        this.registrationDetailsValidator = registrationDetailsValidator;
    }

    @Override
    public User findByEmail(String email) throws ServiceException {
        return tryCallJPA(() -> userRepository.findByEmail(email));
    }

    @Override
    public <S extends User> Optional<S> update(S entity, Integer integer) throws ServiceException {
        Optional<User> updatingUser = wrapToOptional(() -> userRepository.findOne(integer));
        if (updatingUser.isPresent()){
            User existingUser = updatingUser.get();
            entity.setPasswordHash(existingUser.getPasswordHash());
        }
        return super.update(entity, integer);
    }

    @Override
    public void register(@Valid RegistrationDetails registrationDetails, Errors result) throws ServiceException {
        registrationDetailsValidator.validate(registrationDetails, result);
    }
}