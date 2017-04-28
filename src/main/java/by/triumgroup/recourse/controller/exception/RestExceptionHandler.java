package by.triumgroup.recourse.controller.exception;

import by.triumgroup.recourse.entity.dto.ErrorMessage;
import by.triumgroup.recourse.entity.dto.ValidationErrorInfo;
import by.triumgroup.recourse.validation.exception.ServiceAccessDeniedException;
import by.triumgroup.recourse.validation.exception.ServiceBadRequestException;
import by.triumgroup.recourse.validation.exception.ServiceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        Object responseBody = createValidationErrorInfo(bindingResult, status);
        return ResponseEntity.badRequest().body(responseBody);
    }

    @ExceptionHandler(ServiceBadRequestException.class)
    protected ResponseEntity<Object> handleServiceException(ServiceBadRequestException ex) {
        Object responseBody = createValidationErrorInfo(ex.getErrorMessages(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.badRequest().body(responseBody);
    }

    @ExceptionHandler(ServiceNotFoundException.class)
    protected ResponseEntity<Object> handleServiceNotFoundException(ServiceNotFoundException ex) {
        return createResponseEntity(ex.getErrorMessages(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ServiceAccessDeniedException.class)
    protected ResponseEntity<Object> handleServiceAccessDeniedException(ServiceAccessDeniedException ex) {
        return createResponseEntity(ex.getErrorMessages(), HttpStatus.FORBIDDEN);
    }

    private ValidationErrorInfo createValidationErrorInfo(BindingResult bindingResult, HttpStatus status) {
        return new ValidationErrorInfo(
                status.getReasonPhrase(),
                status.value(),
                bindingResult
                        .getFieldErrors()
                        .stream()
                        .map(ErrorMessage::fromFieldError)
                        .collect(Collectors.toList()));
    }

    private ValidationErrorInfo createValidationErrorInfo(List<ErrorMessage> messages, HttpStatus status) {
        return new ValidationErrorInfo(status.getReasonPhrase(), status.value(), messages);
    }

    private ResponseEntity<Object> createResponseEntity(List<ErrorMessage> messages, HttpStatus status){
        return new ResponseEntity<>(createValidationErrorInfo(messages, status), status);
    }
}
