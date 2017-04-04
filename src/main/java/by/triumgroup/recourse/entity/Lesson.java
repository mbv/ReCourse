package by.triumgroup.recourse.entity;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "lesson")
public class Lesson extends BaseEntity<Integer>{
    @Column(columnDefinition = "DATETIME", nullable = false)
    private Timestamp startTime;

    @Column(columnDefinition = "TIME", nullable = false)
    private Time duration;

    @Column(columnDefinition = "INT(11)", nullable = false)
    private Long courseId;

    @Column(length = 50)
    private String topic;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "teacher_id")
    private User teacher;

    public Lesson() {
    }

    public Lesson(Timestamp startTime, Time duration, Long courseId, String topic, User teacher) {
        this.startTime = startTime;
        this.duration = duration;
        this.courseId = courseId;
        this.topic = topic;
        this.teacher = teacher;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

}
