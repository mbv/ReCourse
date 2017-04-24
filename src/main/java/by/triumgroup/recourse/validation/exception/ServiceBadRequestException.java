package by.triumgroup.recourse.validation.exception;

import by.triumgroup.recourse.entity.dto.ErrorMessage;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

public class ServiceBadRequestException extends ValidationException {
    private List<ErrorMessage> errorMessages;

    public ServiceBadRequestException(BindingResult bindingResult) {
        errorMessages = bindingResult
                .getFieldErrors()
                .stream()
                .map(ErrorMessage::fromFieldError)
                .collect(Collectors.toList());
    }

    public ServiceBadRequestException(List<ErrorMessage> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public List<ErrorMessage> getErrorMessages() {
        return errorMessages;
    }
}
