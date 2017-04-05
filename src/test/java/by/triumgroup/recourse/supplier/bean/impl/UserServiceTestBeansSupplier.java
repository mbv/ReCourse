package by.triumgroup.recourse.supplier.bean.impl;

import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.service.UserService;
import by.triumgroup.recourse.service.impl.UserServiceImpl;
import by.triumgroup.recourse.supplier.bean.DefaultTestBeansSupplier;
import by.triumgroup.recourse.validation.RegistrationDetailsValidator;
import org.mockito.Mockito;

public class UserServiceTestBeansSupplier extends DefaultTestBeansSupplier<UserService, UserRepository> {
    public UserServiceTestBeansSupplier() {
        super(UserServiceImpl.class, UserRepository.class);
    }

    @Override
    protected void createTestBeans() {
        mockedBean = Mockito.mock(UserRepository.class);
        beanUnderTest = new UserServiceImpl(mockedBean, new RegistrationDetailsValidator(mockedBean));
    }
}
