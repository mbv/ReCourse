package by.triumgroup.recourse.entity.model;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "mark")
public class Mark extends BaseEntity<Integer> {

    @NotNull(message = "Score is not specified")
    @Range(min = 0, max = 10, message = "Score must be in range 1-100")
    @Column(columnDefinition = "TINYINT", nullable = false)
    private Integer score;

    @NotNull(message = "Solution ID is not specified")
    @Column(columnDefinition = "INT(11)", nullable = false)
    private Integer solutionId;

    @NotNull(message = "Comment is not specified")
    @SafeHtml
    @Size(min = 1, max = 2000, message = "Comment length must be in range 1-2000")
    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment;

    public Mark() {
    }

    public Mark(int id, int score, Integer solutionId, String comment) {
        super(id);
        this.score = score;
        this.solutionId = solutionId;
        this.comment = comment;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(Integer solutionId) {
        this.solutionId = solutionId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Mark mark = (Mark) o;
        return Objects.equals(score, mark.score) &&
                Objects.equals(solutionId, mark.solutionId) &&
                Objects.equals(comment, mark.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), score, solutionId, comment);
    }
}
