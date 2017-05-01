package by.triumgroup.recourse.util;

import by.triumgroup.recourse.controller.exception.AccessDeniedException;
import by.triumgroup.recourse.controller.exception.ControllerException;
import by.triumgroup.recourse.controller.exception.RequestException;
import by.triumgroup.recourse.service.exception.ServiceAccessDeniedException;
import by.triumgroup.recourse.service.exception.ServiceException;
import by.triumgroup.recourse.service.exception.ServiceRequestException;
import by.triumgroup.recourse.util.WrapperFunctions.WrapperFunction;
import by.triumgroup.recourse.util.WrapperFunctions.WrapperVoidFunction;
import org.slf4j.Logger;

public class ServiceCallWrapper {

    public static <R> R wrapServiceCall(Logger logger, WrapperFunction<R> function) {
        try {
            return function.call();
        } catch (ServiceAccessDeniedException e) {
            logger.trace("Fail to call service: access denied ", e);
            throw new AccessDeniedException();
        } catch (ServiceRequestException e) {
            logger.trace("Fail to call service: bad request ", e);
            throw new RequestException(e.getStatus(), e.getErrorMessages());
        } catch (ServiceException e) {
            logger.warn("Fail to call service\n", e);
            throw new ControllerException(e);
        }
    }

    public static void wrapServiceCall(Logger logger, WrapperVoidFunction function) {
        wrapServiceCall(logger, () -> {
            function.call();
            return null;
        });
    }

}
