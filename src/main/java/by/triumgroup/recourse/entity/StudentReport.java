package by.triumgroup.recourse.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "student_report")
public class StudentReport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "INT(11)", nullable = false)
    private Long id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "student_id")
    private User student;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "teacher_id")
    private User teacher;

    @Column(columnDefinition = "INT(11)", nullable = false)
    private Long courseId;

    @Column(length = 50)
    private String heading;

    @Column(columnDefinition = "TEXT")
    private String report;

    public StudentReport() {
    }

    public StudentReport(Long id, User student, User teacher, Long courseId, String heading, String report) {
        this.id = id;
        this.student = student;
        this.teacher = teacher;
        this.courseId = courseId;
        this.heading = heading;
        this.report = report;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
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
        StudentReport that = (StudentReport) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(courseId, that.courseId) &&
                Objects.equals(student, that.student) &&
                Objects.equals(teacher, that.teacher) &&
                Objects.equals(heading, that.heading) &&
                Objects.equals(report, that.report);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, student, teacher, courseId, heading, report);
    }

    @Override
    public String toString() {
        return "StudentReport{" +
                "id=" + id +
                ", student=" + student +
                ", teacher=" + teacher +
                ", courseId=" + courseId +
                ", heading='" + heading + '\'' +
                ", report='" + report + '\'' +
                '}';
    }
}
