package by.triumgroup.recourse.validation.exception;

import by.triumgroup.recourse.entity.dto.ErrorMessage;

import java.util.List;

public class ServiceNotFoundException extends ValidationException {
    public ServiceNotFoundException(List<ErrorMessage> messages) {
        super(messages);
    }
}
