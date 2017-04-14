package by.triumgroup.recourse.entity.model;

import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "hometask")
public class Hometask extends BaseEntity<Integer>{

    @NotNull
    @Column(columnDefinition = "INT(11)", nullable = false)
    private Integer lessonId;

    @NotNull
    @SafeHtml
    @Column(columnDefinition = "TEXT", nullable = false)
    private String task;

    public Hometask() {
    }

    public Hometask(Integer lessonId, String task) {
        this.lessonId = lessonId;
        this.task = task;
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Hometask hometask = (Hometask) o;
        return Objects.equals(lessonId, hometask.lessonId) &&
                Objects.equals(task, hometask.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), lessonId, task);
    }
}
