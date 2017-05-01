package by.triumgroup.recourse.service.exception;

import by.triumgroup.recourse.entity.dto.ErrorMessage;
import org.springframework.http.HttpStatus;

import java.util.List;

public class ServiceNotFoundException extends ServiceRequestException {

    public ServiceNotFoundException(List<ErrorMessage> messages) {
        super(HttpStatus.NOT_FOUND, messages);
    }

    public ServiceNotFoundException(ErrorMessage... message) {
        super(HttpStatus.NOT_FOUND, message);
    }

}
