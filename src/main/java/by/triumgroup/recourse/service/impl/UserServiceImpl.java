package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.User;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.service.UserService;
import by.triumgroup.recourse.service.exception.ServiceException;

import static by.triumgroup.recourse.service.exception.wrapper.ServiceExceptionWrapper.tryCallJPA;

public class UserServiceImpl extends AbstractCrudService<User, Long> implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    @Override
    public User findByEmail(String email) throws ServiceException {
        return tryCallJPA(() -> userRepository.findByEmail(email));
    }
}
