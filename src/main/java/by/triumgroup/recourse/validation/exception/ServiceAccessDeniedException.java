package by.triumgroup.recourse.validation.exception;

import by.triumgroup.recourse.entity.dto.ErrorMessage;

public class ServiceAccessDeniedException extends ValidationException {
    public ServiceAccessDeniedException(ErrorMessage message) {
        super(message);
    }
}
