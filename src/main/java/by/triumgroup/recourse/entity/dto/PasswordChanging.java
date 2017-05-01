package by.triumgroup.recourse.entity.dto;

import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PasswordChanging {
    @NotNull(message = "Password is not specified")
    @Size(min = 8, max = 50, message = "Password length must be in range 8-50")
    @SafeHtml
    private String password;

    @NotNull(message = "Password confirmation is not specified")
    @Size(min = 8, max = 50, message = "Password confirmation length must be in range 8-50")
    @SafeHtml
    private String passwordConfirmation;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }
}
