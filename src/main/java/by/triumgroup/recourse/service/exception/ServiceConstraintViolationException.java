package by.triumgroup.recourse.service.exception;

import by.triumgroup.recourse.entity.dto.ErrorMessage;
import org.springframework.http.HttpStatus;

import java.util.List;

public class ServiceConstraintViolationException extends ServiceRequestException {

    public ServiceConstraintViolationException(ErrorMessage... message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

    public ServiceConstraintViolationException(List<ErrorMessage> messages) {
        super(HttpStatus.BAD_REQUEST, messages);
    }
}
