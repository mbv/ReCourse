package by.triumgroup.recourse.entity.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PasswordChanging {
    @NotNull
    @Size(min = 8)
    private String password;

    @NotNull
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
