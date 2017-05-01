package by.triumgroup.recourse.controller.exception;

import by.triumgroup.recourse.entity.dto.ErrorMessage;
import by.triumgroup.recourse.entity.dto.RestError;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import static by.triumgroup.recourse.controller.exception.WhiteLabelErrorPageController.ERROR_PATH;

@Controller
@RequestMapping(ERROR_PATH)
public class WhiteLabelErrorPageController implements ErrorController {

    public static final String ERROR_PATH = "/error";
    public static final String ERROR_HTML_PATH = "/error/404.html";

    @GetMapping("")
    public Object error(HttpServletRequest request) {
        String accept = request.getHeader(HttpHeaders.ACCEPT);
        if (accept != null && accept.contains(MediaType.APPLICATION_JSON_VALUE)) {
            return new ResponseEntity<>(new RestError(HttpStatus.NOT_FOUND,
                    new ErrorMessage("Invalid path", request.getRequestURI())),
                    HttpStatus.NOT_FOUND);
        } else {
            return ERROR_HTML_PATH;
        }
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

}