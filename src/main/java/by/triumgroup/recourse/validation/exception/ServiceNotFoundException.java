package by.triumgroup.recourse.validation.exception;

import by.triumgroup.recourse.entity.dto.ErrorMessage;

import java.util.List;

public class ServiceNotFoundException extends ValidationException {

    private final List<ErrorMessage> messages;

    public ServiceNotFoundException(List<ErrorMessage> messages) {
        this.messages = messages;
    }

    public List<ErrorMessage> getErrorMessages() {
        return messages;
    }
}
