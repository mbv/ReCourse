package by.triumgroup.recourse.entity;

import javax.persistence.*;

@Entity
@Table(name = "hometask_solution")
public class HometaskSolution extends BaseEntity<Integer>{
    @Column(columnDefinition = "INT(11)", nullable = false)
    private Long hometaskId;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "student_id")
    private User student;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String solution;

    public HometaskSolution() {
    }

    public HometaskSolution(Long hometaskId, User student, String solution) {
        this.hometaskId = hometaskId;
        this.student = student;
        this.solution = solution;
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
}
