package by.triumgroup.recourse.entity;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "mark")
public class Mark extends BaseEntity<Integer> {

    @NotNull
    @Min(0)
    @Max(10)
    @Column(columnDefinition = "TINYINT", nullable = false)
    private Integer score;

    @NotNull
    @Column(columnDefinition = "INT(11)", nullable = false)
    private Long solutionId;

    @NotNull
    @SafeHtml
    @NotEmpty
    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment;

    public Mark() {
    }

    public Mark(int score, Long solutionId, String comment) {
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

    public Long getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(Long solutionId) {
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
