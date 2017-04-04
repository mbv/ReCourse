package by.triumgroup.recourse.entity;

import javax.persistence.*;

@Entity
@Table(name = "course_feedback")
public class CourseFeedback extends BaseEntity<Integer>{

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
}
