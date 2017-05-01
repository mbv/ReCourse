package by.triumgroup.recourse.validation.exception;

import by.triumgroup.recourse.entity.dto.ErrorMessage;
import org.springframework.http.HttpStatus;

import java.util.List;

public class ServiceAccessDeniedException extends ServiceRequestException {
    public ServiceAccessDeniedException(ErrorMessage... message) {
        super(HttpStatus.FORBIDDEN, message);
    }

    public ServiceAccessDeniedException(List<ErrorMessage> messages) {
        super(HttpStatus.FORBIDDEN, messages);
    }
}
