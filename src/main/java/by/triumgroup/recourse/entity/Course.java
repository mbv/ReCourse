package by.triumgroup.recourse.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "INT(11)", nullable = false)
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "teacher_id")
    private User teacher;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "organizer_id")
    private User organizer;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM ('ONGOING', 'REGISTRATION', 'FINISHED')", nullable = false)
    private Status status;

    private Integer maxStudents;

    public Course() {
    }

    public Course(Long id, String title, String description, User teacher, User organizer, Status status, Integer maxStudents) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.teacher = teacher;
        this.organizer = organizer;
        this.status = status;
        this.maxStudents = maxStudents;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        Course course = (Course) o;
        return Objects.equals(id, course.id) &&
                Objects.equals(maxStudents, course.maxStudents) &&
                Objects.equals(title, course.title) &&
                Objects.equals(description, course.description) &&
                Objects.equals(teacher, course.teacher) &&
                Objects.equals(organizer, course.organizer) &&
                status == course.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, teacher, organizer, status, maxStudents);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", teacher=" + teacher +
                ", organizer=" + organizer +
                ", status=" + status +
                ", maxStudents=" + maxStudents +
                '}';
    }

    public enum Status {
        ONGOING, REGISTRATION, FINISHED
    }
}
