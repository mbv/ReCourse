package by.triumgroup.recourse.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "course_feedback")
public class CourseFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "INT(11)", nullable = false)
    private Long id;

    @Column(columnDefinition = "INT(11)", nullable = false)
    private Long courseId;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "student_id")
    private User student;

    @Column(length = 50)
    private String heading;

    @Column(columnDefinition = "TEXT")
    private String report;

    @Column(columnDefinition = "TEXT")
    private String pros;

    @Column(columnDefinition = "TEXT")
    private String cons;

    public CourseFeedback() {
    }

    public CourseFeedback(Long id, Long courseId, User student, String heading, String report, String pros, String cons) {
        this.id = id;
        this.courseId = courseId;
        this.student = student;
        this.heading = heading;
        this.report = report;
        this.pros = pros;
        this.cons = cons;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        CourseFeedback that = (CourseFeedback) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(courseId, that.courseId) &&
                Objects.equals(student, that.student) &&
                Objects.equals(heading, that.heading) &&
                Objects.equals(report, that.report) &&
                Objects.equals(pros, that.pros) &&
                Objects.equals(cons, that.cons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseId, student, heading, report, pros, cons);
    }

    @Override
    public String toString() {
        return "CourseFeedback{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", student=" + student +
                ", heading='" + heading + '\'' +
                ", report='" + report + '\'' +
                ", pros='" + pros + '\'' +
                ", cons='" + cons + '\'' +
                '}';
    }
}
