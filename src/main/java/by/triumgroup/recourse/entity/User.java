package by.triumgroup.recourse.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "INT(11)", nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(columnDefinition = "CHAR(60)", nullable = false)
    private String passwordHash;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String surname;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM ('MALE', 'FEMALE')", nullable = false)
    private Gender gender;

    @Column(columnDefinition = "DATE")
    private Timestamp birthday;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM ('GUEST', 'STUDENT', 'TEACHER', 'ORGANIZER')")
    private Role role;

    private boolean isDeleted;

    public User() {
    }

    public User(User user) {
        id = user.id;
        email = user.email;
        passwordHash = user.passwordHash;
        name = user.name;
        surname = user.surname;
        gender = user.gender;
        birthday = user.birthday;
        role = user.role;
        isDeleted = user.isDeleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                isDeleted == user.isDeleted &&
                Objects.equals(email, user.email) &&
                Objects.equals(passwordHash, user.passwordHash) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                gender == user.gender &&
                Objects.equals(birthday, user.birthday) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, passwordHash, name, surname, gender, birthday, role, isDeleted);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                //", passwordHash='" + passwordHash + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", role=" + role +
                ", isDeleted=" + isDeleted +
                '}';
    }

    public enum Gender {
        MALE, FEMALE
    }

    public enum Role {
        GUEST, STUDENT, TEACHER, ORGANIZER
    }
}
