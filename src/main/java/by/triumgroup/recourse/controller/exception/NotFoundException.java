package by.triumgroup.recourse.controller.exception;

import by.triumgroup.recourse.entity.dto.ErrorMessage;
import org.springframework.http.HttpStatus;

import static by.triumgroup.recourse.validation.support.Constants.DEFAULT_ERROR_TITLE;

public class NotFoundException extends RequestException {

    public NotFoundException() {
        super(HttpStatus.NOT_FOUND, new ErrorMessage(DEFAULT_ERROR_TITLE, "Entity not found"));

    }

}
