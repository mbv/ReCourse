package by.triumgroup.recourse.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Table(name = "user")
public class User extends BaseEntity<Integer> {
    @Column(nullable = false, unique = true)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(columnDefinition = "CHAR(60)", nullable = false)
    private String passwordHash;

    @Column(length = 50, nullable = false)
    @Size(min = 1, max = 50)
    @NotNull
    private String name;

    @Column(length = 50, nullable = false)
    @Size(min = 1, max = 50)
    @NotNull
    private String surname;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM ('MALE', 'FEMALE')", nullable = false)
    @NotNull
    private Gender gender;

    @Column(columnDefinition = "DATE")
    private Timestamp birthday;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM ('GUEST', 'STUDENT', 'TEACHER', 'ORGANIZER')")
    @NotNull
    private Role role;

    private boolean isDeleted;

    public User() {
    }

    public User(User user) {
        email = user.email;
        passwordHash = user.passwordHash;
        name = user.name;
        surname = user.surname;
        gender = user.gender;
        birthday = user.birthday;
        role = user.role;
        isDeleted = user.isDeleted;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public enum Gender {
        MALE, FEMALE
    }

    public enum Role {
        GUEST, STUDENT, TEACHER, ORGANIZER
    }
}
