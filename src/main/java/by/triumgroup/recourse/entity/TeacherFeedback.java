package by.triumgroup.recourse.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "teacher_feedback")
public class TeacherFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "INT(11)", nullable = false)
    private Long id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "teacher_id")
    private User teacher;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "student_id")
    private User student;

    @Column(length = 50)
    private String heading;

    @Column(columnDefinition = "TEXT")
    private String report;

    public TeacherFeedback() {
    }

    public TeacherFeedback(User teacher, User student, String heading, String report) {
        this.teacher = teacher;
        this.student = student;
        this.heading = heading;
        this.report = report;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherFeedback that = (TeacherFeedback) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(teacher, that.teacher) &&
                Objects.equals(student, that.student) &&
                Objects.equals(heading, that.heading) &&
                Objects.equals(report, that.report);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teacher, student, heading, report);
    }

    @Override
    public String toString() {
        return "TeacherFeedback{" +
                "id=" + id +
                ", teacher=" + teacher +
                ", student=" + student +
                ", heading='" + heading + '\'' +
                ", report='" + report + '\'' +
                '}';
    }
}
