package by.triumgroup.recourse.entity;

import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

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
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "teacher_id")
    private User teacher;

    @NotNull
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "organizer_id")
    private User organizer;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM ('ONGOING', 'REGISTRATION', 'FINISHED')", nullable = false)
    private Status status;

    @NotNull
    @Min(1)
    @Max(100)
    private Integer maxStudents;

    public Course() {
    }

    public Course(String title, String description, User teacher, User organizer, Status status, Integer maxStudents) {
        this.title = title;
        this.description = description;
        this.teacher = teacher;
        this.organizer = organizer;
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

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public User getOrganizer() {
        return organizer;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Course course = (Course) o;
        return Objects.equals(title, course.title) &&
                Objects.equals(description, course.description) &&
                Objects.equals(teacher, course.teacher) &&
                Objects.equals(organizer, course.organizer) &&
                status == course.status &&
                Objects.equals(maxStudents, course.maxStudents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, description, teacher, organizer, status, maxStudents);
    }

    public enum Status {
        ONGOING, REGISTRATION, FINISHED
    }
}
