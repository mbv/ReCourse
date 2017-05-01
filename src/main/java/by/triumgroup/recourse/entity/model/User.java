package by.triumgroup.recourse.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

import static by.triumgroup.recourse.validation.support.Constants.PATTERN;

@Entity
@Table(name = "user")
public class User extends BaseEntity<Integer> {

    @NotNull(message = "Email is not specified")
    @Email(regexp = PATTERN, message = "Email is malformed")
    @Size(min = 1, max = 255, message = "Email length must be in range 1-255")
    @SafeHtml
    @Column(nullable = false, unique = true)
    private String email;

    @Size(min = 60, max = 60, message = "Password hash length must be 60 chars")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(columnDefinition = "CHAR(60)", nullable = false)
    private String passwordHash;

    @NotNull(message = "Name is not specified")
    @SafeHtml
    @Size(min = 1, max = 50, message = "Name length must be in range 1-50")
    @Column(length = 50, nullable = false)
    private String name;

    @NotNull(message = "Surname is not specified")
    @SafeHtml
    @Size(min = 1, max = 50, message = "Surname length must be in range 1-50")
    @Column(length = 50, nullable = false)
    private String surname;

    @NotNull(message = "Gender is not specified")
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM ('MALE', 'FEMALE')", nullable = false)
    private Gender gender;

    @Past(message = "Birthday must be in past")
    @Column(columnDefinition = "DATE")
    private Timestamp birthday;

    @NotNull(message = "Role is not specified")
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM ('STUDENT', 'TEACHER', 'ADMIN')")
    private Role role;

    @JsonIgnore
    @ManyToMany(mappedBy = "students")
    private Set<Course> courses;

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
        setId(user.getId());
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

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) &&
                Objects.equals(passwordHash, user.passwordHash) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                gender == user.gender &&
                Objects.equals(birthday, user.birthday) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), email, passwordHash, name, surname, gender, birthday, role);
    }

    public enum Gender {
        MALE, FEMALE
    }

    public enum Role {
        STUDENT, TEACHER, ADMIN, DISABLED
    }
}
