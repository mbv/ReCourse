package by.triumgroup.recourse.controller.exception;

import by.triumgroup.recourse.entity.dto.ErrorMessage;
import by.triumgroup.recourse.entity.dto.RestError;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/error")
public class WhiteLabelErrorPageController implements ErrorController {

    @GetMapping("")
    public Object error(HttpServletRequest request) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<>(
                    new RestError(HttpStatus.NOT_FOUND, new ErrorMessage("Invalid path", request.getRequestURI())),
                    HttpStatus.NOT_FOUND);
        } else {
            return "/error/404.html";
        }
    }

    @Override
    public String getErrorPath() {
        return "error";
    }

}