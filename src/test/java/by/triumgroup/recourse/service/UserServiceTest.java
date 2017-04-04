package by.triumgroup.recourse.service;

import by.triumgroup.recourse.entity.User;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.service.impl.UserServiceImpl;
import by.triumgroup.recourse.supplier.bean.TestBeansSupplier;
import by.triumgroup.recourse.supplier.entity.impl.UserSupplier;

public class UserServiceTest extends CrudServiceTest<User, Integer, UserService, UserRepository> {

    public UserServiceTest() {
        super(new TestBeansSupplier<>(UserServiceImpl.class, UserRepository.class), new UserSupplier());
    }
}
