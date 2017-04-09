package by.triumgroup.recourse.entity;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Objects;

@Entity
@Table(name = "hometask_solution")
public class HometaskSolution extends BaseEntity<Integer> {

    @NotNull
    @Column(columnDefinition = "INT(11)", nullable = false)
    private Long hometaskId;

    @NotNull
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "student_id")
    private User student;

    @NotNull
    @SafeHtml
    @NotEmpty
    @Column(columnDefinition = "TEXT", nullable = false)
    private String solution;

    @Null
    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "solution_id")
    private Mark mark;

    public HometaskSolution() {
    }

    public HometaskSolution(Long hometaskId, User student, String solution, Mark mark) {
        this.hometaskId = hometaskId;
        this.student = student;
        this.solution = solution;
        this.mark = mark;
    }

    public Long getHometaskId() {
        return hometaskId;
    }

    public void setHometaskId(Long hometaskId) {
        this.hometaskId = hometaskId;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        HometaskSolution that = (HometaskSolution) o;
        return Objects.equals(hometaskId, that.hometaskId) &&
                Objects.equals(student, that.student) &&
                Objects.equals(solution, that.solution) &&
                Objects.equals(mark, that.mark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hometaskId, student, solution, mark);
    }
}
