package by.triumgroup.recourse.entity;

import javax.persistence.*;

@Entity
@Table(name = "student_report")
public class StudentReport extends BaseEntity<Integer>{
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

    public StudentReport( User student, User teacher, Long courseId, String heading, String report) {
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

}
