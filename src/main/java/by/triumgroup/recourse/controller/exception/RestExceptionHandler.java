package by.triumgroup.recourse.controller.exception;

import by.triumgroup.recourse.entity.dto.ErrorMessage;
import by.triumgroup.recourse.entity.dto.OauthError;
import by.triumgroup.recourse.entity.dto.RestError;
import by.triumgroup.recourse.util.Util;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestExceptionHandler extends BaseResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        RestError apiError = createRestError(bindingResult, status);
        return handleExceptionInternal(ex, apiError, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Throwable cause = ex.getCause();
        ErrorMessage errorMessage;
        if (cause instanceof InvalidFormatException) {
            InvalidFormatException e = (InvalidFormatException) cause;
            errorMessage = new ErrorMessage(
                    "Invalid entity",
                    "Invalid value '" + e.getValue() + "' of type " + e.getTargetType().getSimpleName()
            );
        } else if (cause instanceof JsonParseException) {
            errorMessage = new ErrorMessage("Invalid JSON", "Invalid JSON format");
        } else {
            errorMessage = new ErrorMessage(
                    "Message not readable",
                    Util.ifNullDefault(cause.getMessage(), "Unknown error")
            );
        }
        return handleExceptionInternal(ex, createRestError(status, errorMessage), headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(
                "Invalid path variable",
                "Invalid value '" + ex.getValue() + "' for type '" + ex.getRequiredType().getSimpleName() + "'"
        );
        return handleExceptionInternal(ex, createRestError(status, errorMessage), headers, status, request);
    }

    @ExceptionHandler(RequestException.class)
    protected ResponseEntity<Object> handleServiceException(RequestException ex) {
        return createResponseEntity(ex.getStatus(), ex.getErrors());
    }

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
        return new ResponseEntity<>(new OauthError("access_denied", "Access is denied"), HttpStatus.FORBIDDEN);
    }

}
