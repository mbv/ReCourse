package by.triumgroup.recourse.validation.exception;

import org.springframework.validation.BindingResult;

public class ServiceValidationException extends ValidationException {
    private BindingResult bindingResult;

    public ServiceValidationException(BindingResult bindingResult){
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }
}
