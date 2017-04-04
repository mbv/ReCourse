package by.triumgroup.recourse.entity;

import javax.persistence.*;

@Entity
@Table(name = "hometask")
public class Hometask extends BaseEntity<Integer>{
    @Column(columnDefinition = "INT(11)", nullable = false)
    private Long lessonId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String task;

    public Hometask() {
    }

    public Hometask(long lessonId, String task) {
        this.lessonId = lessonId;
        this.task = task;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

}
