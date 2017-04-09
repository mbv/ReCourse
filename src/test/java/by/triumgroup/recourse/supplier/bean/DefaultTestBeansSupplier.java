package by.triumgroup.recourse.supplier.bean;

import org.mockito.Mockito;

import java.lang.reflect.InvocationTargetException;

public class DefaultTestBeansSupplier<TBeanUnderTest, TBeanToMock> {
    private final Class<? extends TBeanUnderTest> beanUnderTestClass;
    private final Class<? extends TBeanToMock> beanToMockClass;

    protected TBeanUnderTest beanUnderTest;
    protected TBeanToMock mockedBean;

    public DefaultTestBeansSupplier(Class<? extends TBeanUnderTest> beanUnderTestClass, Class<? extends TBeanToMock> beanToMockClass) {
        this.beanUnderTestClass = beanUnderTestClass;
        this.beanToMockClass = beanToMockClass;
        createTestBeans();
    }

    public TBeanUnderTest getBeanUnderTest() {
        return beanUnderTest;
    }

    public TBeanToMock getMockedBean() {
        return mockedBean;
    }

    protected void createTestBeans() {
        mockedBean = Mockito.mock(beanToMockClass);
        try {
            beanUnderTest = beanUnderTestClass.getDeclaredConstructor(beanToMockClass).newInstance(mockedBean);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}