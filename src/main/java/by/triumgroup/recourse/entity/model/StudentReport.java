package by.triumgroup.recourse.entity.model;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "student_report")
public class StudentReport extends BaseEntity<Integer>{

    @NotNull
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "student_id")
    private User student;

    @NotNull
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "teacher_id")
    private User teacher;

    @NotNull
    @Column(columnDefinition = "INT(11)", nullable = false)
    private Integer courseId;

    @NotNull
    @SafeHtml
    @Size(min = 1, max = 50)
    @Column(length = 50)
    private String heading;

    @NotBlank
    @SafeHtml
    @Column(columnDefinition = "TEXT")
    private String report;

    public StudentReport() {
    }

    public StudentReport( User student, User teacher, Integer courseId, String heading, String report) {
        this.student = student;
        this.teacher = teacher;
        this.courseId = courseId;
        this.heading = heading;
        this.report = report;
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

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
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
        if (!super.equals(o)) return false;
        StudentReport that = (StudentReport) o;
        return Objects.equals(student, that.student) &&
                Objects.equals(teacher, that.teacher) &&
                Objects.equals(courseId, that.courseId) &&
                Objects.equals(heading, that.heading) &&
                Objects.equals(report, that.report);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), student, teacher, courseId, heading, report);
    }
}
