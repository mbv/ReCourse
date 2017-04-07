package by.triumgroup.recourse.util;

import by.triumgroup.recourse.service.exception.ServiceConstraintViolationException;
import by.triumgroup.recourse.service.exception.ServiceException;
import by.triumgroup.recourse.util.WrapperFunctions.WrapperFunction;
import by.triumgroup.recourse.util.WrapperFunctions.WrapperVoidFunction;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

public class RepositoryCallWrapper {

    public static <R> R wrapJPACall(WrapperFunction<R> function) {
        try {
            return function.call();
        } catch (DataAccessException e) {
            throw new ServiceException(e);
        }
    }

    public static void wrapJPACall(WrapperVoidFunction function) {
        try {
            function.call();
        } catch (DataAccessException e) {
            throw new ServiceException(e);
        }
    }

    public static <T> Optional<T> wrapJPACallToOptional(WrapperFunction<T> function) {
        return wrapJPACall(() -> {
            Optional<T> result;
            try {
                result = Optional.ofNullable(function.call());
            } catch (EmptyResultDataAccessException e) {
                result = Optional.empty();
            } catch (DataIntegrityViolationException e) {
                throw new ServiceConstraintViolationException(e);
            }
            return result;
        });
    }

    public static Optional<Boolean> wrapJPACallToBoolean(WrapperVoidFunction function) {
        return wrapJPACall(() -> {
            Optional<Boolean> result = Optional.of(true);
            try {
                function.call();
            } catch (EmptyResultDataAccessException e) {
                result = Optional.empty();
            } catch (DataIntegrityViolationException e) {
                throw new ServiceConstraintViolationException(e);
            }
            return result;
        });
    }

}
