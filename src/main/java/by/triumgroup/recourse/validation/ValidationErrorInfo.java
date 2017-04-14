package by.triumgroup.recourse.validation;

import java.util.List;

public class ValidationErrorInfo {
    private String error;

    private int status;

    private List<FieldErrorInfo> errors;

    public ValidationErrorInfo(String error, int status, List<FieldErrorInfo> errors) {
        this.error = error;
        this.status = status;
        this.errors = errors;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<FieldErrorInfo> getErrors() {
        return errors;
    }

    public void setErrors(List<FieldErrorInfo> errors) {
        this.errors = errors;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
