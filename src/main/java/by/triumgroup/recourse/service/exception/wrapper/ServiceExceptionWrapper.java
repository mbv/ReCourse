package by.triumgroup.recourse.service.exception.wrapper;

import by.triumgroup.recourse.service.exception.ServiceException;
import org.springframework.dao.DataAccessException;

public class ServiceExceptionWrapper {

    public static <R> R tryCallJPA(RepositoryFunction<R> function) throws ServiceException {
        try {
            return function.call();
        } catch (DataAccessException e) {
            throw new ServiceException(e);
        }
    }

    public static void tryCallJPA(RepositoryVoidFunction function) throws ServiceException {
        try {
            function.call();
        } catch (DataAccessException e) {
            throw new ServiceException(e);
        }
    }

    @FunctionalInterface
    public interface RepositoryFunction<R> {
        R call() throws DataAccessException, ServiceException;
    }

    @FunctionalInterface
    public interface RepositoryVoidFunction {
        void call() throws DataAccessException, ServiceException;
    }


}
