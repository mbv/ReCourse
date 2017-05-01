package by.triumgroup.recourse.controller.exception;

import by.triumgroup.recourse.entity.dto.ErrorMessage;
import org.springframework.http.HttpStatus;

public class NotFoundException extends RequestException {

    public NotFoundException() {
        super(HttpStatus.NOT_FOUND, new ErrorMessage("Error", "Entity not found"));

    }

}
