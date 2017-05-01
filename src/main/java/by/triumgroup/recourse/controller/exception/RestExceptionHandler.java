package by.triumgroup.recourse.controller.exception;

import by.triumgroup.recourse.entity.dto.ErrorMessage;
import by.triumgroup.recourse.entity.dto.OauthError;
import by.triumgroup.recourse.entity.dto.RestError;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
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

import java.util.List;

import static by.triumgroup.recourse.validation.support.Constants.DEFAULT_ERROR_TITLE;

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
        if (cause == null) {
            errorMessage = new ErrorMessage(DEFAULT_ERROR_TITLE, "Required request body is missing");
        } else if (cause instanceof InvalidFormatException) {
            InvalidFormatException e = (InvalidFormatException) cause;
            errorMessage = new ErrorMessage(
                    "Invalid entity",
                    "Invalid value '" + e.getValue() + "' of type " + e.getTargetType().getSimpleName()
            );
        } else if (cause instanceof JsonParseException) {
            errorMessage = new ErrorMessage("Invalid JSON", "Invalid JSON format");
        } else if (cause instanceof JsonMappingException) {
            JsonMappingException e = (JsonMappingException) cause;
            List<JsonMappingException.Reference> references = e.getPath();
            StringBuilder message = new StringBuilder("Invalid values in fields");
            references.forEach(reference -> message.append(" '").append(reference.getFieldName()).append("'"));
            errorMessage = new ErrorMessage("Invalid JSON", message.toString());
        } else {
            errorMessage = new ErrorMessage("Message not readable", "Unknown error");
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
    protected ResponseEntity<Object> handleRequestException(RequestException ex) {
        return createResponseEntity(ex.getStatus(), ex.getErrors());
    }

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
        return new ResponseEntity<>(new OauthError("access_denied", "Access is denied"), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ControllerException.class)
    protected ResponseEntity<Object> handleControllerException(ControllerException ex) {
        return createResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR,
                new ErrorMessage(DEFAULT_ERROR_TITLE, "Internal server error"));
    }

}
