package by.triumgroup.recourse.entity;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "teacher_feedback")
public class TeacherFeedback extends BaseEntity<Integer> {

    @NotNull
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "teacher_id")
    private User teacher;

    @NotNull
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "student_id")
    private User student;

    @NotNull
    @SafeHtml
    @Size(min = 1, max = 50)
    @Column(length = 50)
    private String heading;

    @NotNull
    @SafeHtml
    @NotEmpty
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
        if (!super.equals(o)) return false;
        TeacherFeedback that = (TeacherFeedback) o;
        return Objects.equals(teacher, that.teacher) &&
                Objects.equals(student, that.student) &&
                Objects.equals(heading, that.heading) &&
                Objects.equals(report, that.report);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), teacher, student, heading, report);
    }


}
