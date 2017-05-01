package by.triumgroup.recourse.controller.exception;

import by.triumgroup.recourse.entity.dto.ErrorMessage;
import org.springframework.http.HttpStatus;

public class BadRequestException extends RequestException {

    public BadRequestException() {
        super(HttpStatus.BAD_REQUEST, new ErrorMessage("Error", "Bad request"));
    }

}
