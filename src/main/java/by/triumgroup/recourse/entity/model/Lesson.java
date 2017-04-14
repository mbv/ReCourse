package by.triumgroup.recourse.entity.model;

import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "lesson")
public class Lesson extends BaseEntity<Integer> {

    @NotNull
    @Future
    @Column(columnDefinition = "DATETIME", nullable = false)
    private Timestamp startTime;

    @NotNull
    @Column(columnDefinition = "TIME", nullable = false)
    private Time duration;

    @NotNull
    @Column(columnDefinition = "INT(11)", nullable = false)
    private Integer courseId;

    @NotNull
    @SafeHtml
    @Size(min = 1, max = 50)
    @Column(length = 50)
    private String topic;

    @NotNull
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "teacher_id")
    private User teacher;

    public Lesson() {
    }

    public Lesson(Timestamp startTime, Time duration, Integer courseId, String topic, User teacher) {
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

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Lesson lesson = (Lesson) o;
        return Objects.equals(startTime, lesson.startTime) &&
                Objects.equals(duration, lesson.duration) &&
                Objects.equals(courseId, lesson.courseId) &&
                Objects.equals(topic, lesson.topic) &&
                Objects.equals(teacher, lesson.teacher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), startTime, duration, courseId, topic, teacher);
    }
}
