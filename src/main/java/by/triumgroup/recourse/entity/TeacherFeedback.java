package by.triumgroup.recourse.entity;

import javax.persistence.*;

@Entity
@Table(name = "teacher_feedback")
public class TeacherFeedback extends BaseEntity<Integer>{
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
}
