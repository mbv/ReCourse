package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.User;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.service.UserService;
import by.triumgroup.recourse.service.exception.ServiceException;

import java.util.Optional;

import static by.triumgroup.recourse.util.RepositoryCallWrapper.wrapJPACall;
import static by.triumgroup.recourse.util.RepositoryCallWrapper.wrapJPACallToOptional;

public class UserServiceImpl extends AbstractCrudService<User, Integer> implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    @Override
    public User findByEmail(String email) throws ServiceException {
        return wrapJPACall(() -> userRepository.findByEmail(email));
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
}