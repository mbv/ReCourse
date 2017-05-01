package by.triumgroup.recourse.service.exception;

import by.triumgroup.recourse.entity.dto.ErrorMessage;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class ServiceRequestException extends ServiceException {
    private final List<ErrorMessage> messages;
    private final HttpStatus status;

    public ServiceRequestException(HttpStatus status, ErrorMessage... message) {
        this(status, Arrays.asList(message));
    }

    public ServiceRequestException(HttpStatus status, List<ErrorMessage> messages) {
        this.messages = messages;
        this.status = status;
    }

    public List<ErrorMessage> getErrorMessages() {
        return messages;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
