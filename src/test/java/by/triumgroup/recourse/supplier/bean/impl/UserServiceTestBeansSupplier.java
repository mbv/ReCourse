package by.triumgroup.recourse.supplier.bean.impl;

import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.service.UserService;
import by.triumgroup.recourse.service.impl.UserServiceImpl;
import by.triumgroup.recourse.supplier.bean.DefaultTestBeansSupplier;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceTestBeansSupplier extends DefaultTestBeansSupplier<UserService, UserRepository> {

    private PasswordEncoder passwordEncoder;

    public UserServiceTestBeansSupplier() {
        super(UserServiceImpl.class, UserRepository.class);
    }

    @Override
    protected void createTestBeans() {
        mockedBean = Mockito.mock(UserRepository.class);
        beanUnderTest = new UserServiceImpl(mockedBean, passwordEncoder);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
    }

}
