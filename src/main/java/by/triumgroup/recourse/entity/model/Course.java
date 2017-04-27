package by.triumgroup.recourse.entity.model;

import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "course")
public class Course extends BaseEntity<Integer> {

    @NotNull
    @SafeHtml
    @Column(length = 50, nullable = false)
    private String title;

    @NotNull
    @SafeHtml
    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM ('ONGOING', 'REGISTRATION', 'FINISHED')", nullable = false)
    private Status status;

    @NotNull
    @Min(1)
    @Max(100)
    private Integer maxStudents;

    @ManyToMany
    @JoinTable(
            name="course_student",
            joinColumns=@JoinColumn(name="course_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="student_id", referencedColumnName="id"))
    private Set<User> students;

    public Course() {
    }

    public Course(String title, String description, Status status, Integer maxStudents) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.maxStudents = maxStudents;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(Integer maxStudents) {
        this.maxStudents = maxStudents;
    }

    public Set<User> getStudents() {
        return students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Course course = (Course) o;
        return Objects.equals(title, course.title) &&
                Objects.equals(description, course.description) &&
                status == course.status &&
                Objects.equals(maxStudents, course.maxStudents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, description, status, maxStudents);
    }

    public enum Status {
        ONGOING, REGISTRATION, FINISHED
    }
}
