package by.triumgroup.recourse.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "INT(11)", nullable = false)
    private long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(columnDefinition = "CHAR(60)", nullable = false)
    private String passwordHash;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String surname;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('male','female')", nullable = false)
    private Gender gender;

    @Column(columnDefinition = "DATE")
    private Timestamp birthday;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM ('guest', 'student', 'teacher', 'organizer')")
    private Role role;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id == user.id
                && (email != null ? email.equals(user.email) : user.email == null)
                && (passwordHash != null ? passwordHash.equals(user.passwordHash) : user.passwordHash == null)
                && (name != null ? name.equals(user.name) : user.name == null)
                && (surname != null ? surname.equals(user.surname) : user.surname == null)
                && gender == user.gender
                && (birthday != null ? birthday.equals(user.birthday) : user.birthday == null)
                && role == user.role;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (passwordHash != null ? passwordHash.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    public enum Gender {
        MALE, FEMALE
    }

    public enum Role {
        GUEST, STUDENT, TEACHER, ORGANIZER
    }
}
