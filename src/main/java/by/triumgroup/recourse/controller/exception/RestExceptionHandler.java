package by.triumgroup.recourse.controller.exception;

import by.triumgroup.recourse.validation.FieldErrorInfo;
import by.triumgroup.recourse.validation.ValidationErrorInfo;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult defaultResult = ex.getBindingResult();
        ValidationErrorInfo responseBody = new ValidationErrorInfo(
                status.getReasonPhrase(),
                status.value(),
                defaultResult.getFieldErrors().stream().map(FieldErrorInfo::fromFieldError).
            collect(Collectors.toList()));
        return handleExceptionInternal(ex, responseBody, headers, status, request);
    }
}
