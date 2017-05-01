package by.triumgroup.recourse.service.exception;

import by.triumgroup.recourse.entity.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceBadRequestException extends ServiceRequestException {
    public ServiceBadRequestException(BindingResult bindingResult) {
        super(HttpStatus.BAD_REQUEST, bindingResult
                .getFieldErrors()
                .stream()
                .map(ErrorMessage::fromFieldError)
                .collect(Collectors.toList()));
    }

    public ServiceBadRequestException(List<ErrorMessage> errorMessages) {
        super(HttpStatus.BAD_REQUEST, errorMessages);
    }

    public ServiceBadRequestException(String title, String message) {
        super(HttpStatus.BAD_REQUEST, new ErrorMessage(title, message));
    }

    public ServiceBadRequestException(ErrorMessage... errorMessages) {
        super(HttpStatus.BAD_REQUEST, Arrays.asList(errorMessages));
    }
}
