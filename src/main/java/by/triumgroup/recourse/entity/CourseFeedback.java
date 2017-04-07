package by.triumgroup.recourse.entity;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "course_feedback")
public class CourseFeedback extends BaseEntity<Integer> {

    @NotNull
    @Column(columnDefinition = "INT(11)", nullable = false)
    private Long courseId;

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

    @NotNull
    @SafeHtml
    @NotEmpty
    @Column(columnDefinition = "TEXT")
    private String pros;

    @NotNull
    @SafeHtml
    @NotEmpty
    @Column(columnDefinition = "TEXT")
    private String cons;

    public CourseFeedback() {
    }

    public CourseFeedback(Long courseId, User student, String heading, String report, String pros, String cons) {
        this.courseId = courseId;
        this.student = student;
        this.heading = heading;
        this.report = report;
        this.pros = pros;
        this.cons = cons;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
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

    public String getPros() {
        return pros;
    }

    public void setPros(String pros) {
        this.pros = pros;
    }

    public String getCons() {
        return cons;
    }

    public void setCons(String cons) {
        this.cons = cons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CourseFeedback that = (CourseFeedback) o;
        return Objects.equals(courseId, that.courseId) &&
                Objects.equals(student, that.student) &&
                Objects.equals(heading, that.heading) &&
                Objects.equals(report, that.report) &&
                Objects.equals(pros, that.pros) &&
                Objects.equals(cons, that.cons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), courseId, student, heading, report, pros, cons);
    }
}
