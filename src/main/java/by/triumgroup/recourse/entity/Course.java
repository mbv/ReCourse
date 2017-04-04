package by.triumgroup.recourse.entity;

import javax.persistence.*;

@Entity
@Table(name = "course")
public class Course extends BaseEntity<Integer>{
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

    public enum Status {
        ONGOING, REGISTRATION, FINISHED
    }
}
