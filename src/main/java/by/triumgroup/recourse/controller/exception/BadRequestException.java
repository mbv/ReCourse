package by.triumgroup.recourse.controller.exception;

import by.triumgroup.recourse.entity.dto.ErrorMessage;
import org.springframework.http.HttpStatus;

import static by.triumgroup.recourse.validation.support.Constants.DEFAULT_ERROR_TITLE;

public class BadRequestException extends RequestException {

    public BadRequestException() {
        super(HttpStatus.BAD_REQUEST, new ErrorMessage(DEFAULT_ERROR_TITLE, "Bad request"));
    }

}
