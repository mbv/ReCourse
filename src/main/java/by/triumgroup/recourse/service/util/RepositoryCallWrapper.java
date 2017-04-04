package by.triumgroup.recourse.service.util;

import by.triumgroup.recourse.service.exception.ServiceException;
import by.triumgroup.recourse.service.exception.wrapper.ServiceExceptionWrapper.RepositoryFunction;
import by.triumgroup.recourse.service.exception.wrapper.ServiceExceptionWrapper.RepositoryVoidFunction;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

public class RepositoryCallWrapper {
    public static <T> Optional<T> wrapToOptional(RepositoryFunction<T> function) throws ServiceException {
        Optional<T> result;
        try {
            result = Optional.ofNullable(function.call());
        } catch (EmptyResultDataAccessException e){
            result = Optional.empty();
        } catch (DataAccessException e){
            throw new ServiceException(e);
        }

        return result;
    }

    public static Optional<Boolean> wrapToBoolean(RepositoryVoidFunction function) throws ServiceException {
        Optional<Boolean> result = Optional.of(true);
        try {
            function.call();
        } catch (EmptyResultDataAccessException e){
            result = Optional.empty();
        } catch (DataAccessException e){
            throw new ServiceException(e);
        }
        return result;
    }
}
