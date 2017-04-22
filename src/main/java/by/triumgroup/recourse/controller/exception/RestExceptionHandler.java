package by.triumgroup.recourse.controller.exception;

import by.triumgroup.recourse.entity.dto.FieldErrorInfo;
import by.triumgroup.recourse.entity.dto.ValidationErrorInfo;
import by.triumgroup.recourse.validation.exception.ServiceValidationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        Object responseBody = createValidationErrorInfo(bindingResult, status);
        return ResponseEntity.badRequest().body(responseBody);
    }

    @ExceptionHandler(ServiceValidationException.class)
    protected ResponseEntity<Object> handleServiceException(ServiceValidationException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        Object responseBody = createValidationErrorInfo(bindingResult, HttpStatus.BAD_REQUEST);
        return ResponseEntity.badRequest().body(responseBody);
    }

    private ValidationErrorInfo createValidationErrorInfo(BindingResult bindingResult, HttpStatus status) {
        return new ValidationErrorInfo(
                status.getReasonPhrase(),
                status.value(),
                bindingResult
                        .getFieldErrors()
                        .stream()
                        .map(FieldErrorInfo::fromFieldError)
                        .collect(Collectors.toList()));
    }
}
