package by.triumgroup.recourse.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "mark")
public class Mark extends BaseEntity<Integer>{
    @Column(columnDefinition = "TINYINT", nullable = false)
    private int score;

    @Column(columnDefinition = "INT(11)", nullable = false)
    private Long solutionId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment;

    public Mark() {
    }

    public Mark(int score, Long solutionId, String comment) {
        this.score = score;
        this.solutionId = solutionId;
        this.comment = comment;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
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
}
