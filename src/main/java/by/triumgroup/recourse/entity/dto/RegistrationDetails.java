package by.triumgroup.recourse.entity.dto;

import by.triumgroup.recourse.entity.model.User.Gender;
import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

public class RegistrationDetails {
    @Email
    @NotNull
    private String email;

    @Size(min = 1, max = 50)
    @NotNull
    private String name;

    @Size(min = 1, max = 50)
    @NotNull
    private String surname;

    @NotNull
    @Size(min = 8)
    private String password;

    @NotNull
    @Size(min = 8)
    private String passwordConfirmation;

    @NotNull
    private Gender gender;

    @Past
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
