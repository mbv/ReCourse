package by.triumgroup.recourse.entity.dto;

import by.triumgroup.recourse.entity.model.User.Gender;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

import static by.triumgroup.recourse.validation.support.Constants.PATTERN;

public class RegistrationDetails {

    @NotNull(message = "Email is not specified")
    @Email(regexp = PATTERN, message = "Email is malformed")
    @Size(min = 1, max = 255, message = "Email length must be in range 1-255")
    @SafeHtml
    private String email;

    @NotNull(message = "Name is not specified")
    @SafeHtml
    @Size(min = 1, max = 50, message = "Name length must be in range 1-50")
    private String name;

    @NotNull(message = "Surname is not specified")
    @SafeHtml
    @Size(min = 1, max = 50, message = "Surname length must be in range 1-50")
    private String surname;

    @NotNull(message = "Password is not specified")
    @Size(min = 8, max = 50, message = "Password length must be in range 8-50")
    @SafeHtml
    private String password;

    @NotNull(message = "Password confirmation is not specified")
    @Size(min = 8, max = 50, message = "Password confirmation length must be in range 8-50")
    @SafeHtml
    private String passwordConfirmation;

    @NotNull(message = "Gender is not specified")
    private Gender gender;

    @Past(message = "Birthday must be in past")
    @Column(columnDefinition = "DATE")
    private Timestamp birthday;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

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
