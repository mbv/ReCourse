package by.triumgroup.recourse.supplier.bean;

import org.mockito.Mockito;

import java.lang.reflect.InvocationTargetException;

public class TestBeansSupplier<TBeanUnderTest, TBeanToMock> {
    private TBeanUnderTest beanUnderTest;
    private TBeanToMock mockedBean;

    public TestBeansSupplier(Class<? extends TBeanUnderTest> beanUnderTestClass, Class<? extends TBeanToMock> beanToMockClass) {
        mockedBean = Mockito.mock(beanToMockClass);
        try {
            beanUnderTest = beanUnderTestClass.getDeclaredConstructor(beanToMockClass).newInstance(mockedBean);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public TBeanUnderTest getBeanUnderTest() {
        return beanUnderTest;
    }

    public TBeanToMock getMockedBean() {
        return mockedBean;
    }
}