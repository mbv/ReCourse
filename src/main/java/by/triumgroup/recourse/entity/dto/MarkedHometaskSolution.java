package by.triumgroup.recourse.entity.dto;

import by.triumgroup.recourse.entity.model.HometaskSolution;
import by.triumgroup.recourse.entity.model.Mark;
import by.triumgroup.recourse.entity.model.User;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Objects;


public class MarkedHometaskSolution {

    private Integer id;

    @NotNull(message = "Lesson ID is not specified")
    private Integer lessonId;

    @NotNull(message = "Student is not specified")
    private User student;

    @NotNull(message = "Solution is not specified")
    @SafeHtml
    @Size(min = 1, max = 2000, message = "Solution length must be in range 1-2000")
    private String solution;

    @Valid
    private Mark mark;

    public MarkedHometaskSolution(Integer id, Integer lessonId, User student, String solution, Mark mark) {
        this.id = id;
        this.lessonId = lessonId;
        this.student = student;
        this.solution = solution;
        this.mark = mark;
    }

    @PersistenceConstructor
    public MarkedHometaskSolution(
            Integer solutionId,
            Integer solutionLessonId,
            String solutionSolution,
            Integer studentId,
            String studentEmail,
            String studentName,
            String studentSurname,
            String studentGender,
            String studentBirthday,
            String studentRole,
            Integer markId,
            Integer markScore,
            String markComment
    ) {
        this.id = solutionId;
        this.lessonId = solutionLessonId;
        this.solution = solutionSolution;
        User student = new User();
        student.setId(studentId);
        student.setEmail(studentEmail);
        student.setName(studentName);
        student.setSurname(studentSurname);
        student.setGender(User.Gender.valueOf(studentGender));
        student.setBirthday(studentBirthday == null ? null : Timestamp.valueOf(studentBirthday));
        student.setRole(User.Role.valueOf(studentRole));
        this.student = student;
        if (markId == null && markScore == null && markComment == null) {
            this.mark = null;
        } else {
            Mark mark = new Mark();
            mark.setId(markId);
            mark.setSolutionId(solutionId);
            mark.setScore(markScore);
            mark.setComment(markComment);
            this.mark = mark;
        }
    }

    public MarkedHometaskSolution() {
    }

    public static MarkedHometaskSolution from(HometaskSolution hometaskSolution, Mark mark) {
        return new MarkedHometaskSolution(
                hometaskSolution.getId(),
                hometaskSolution.getLessonId(),
                hometaskSolution.getStudent(),
                hometaskSolution.getSolution(),
                mark
        );
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarkedHometaskSolution that = (MarkedHometaskSolution) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(lessonId, that.lessonId) &&
                Objects.equals(student, that.student) &&
                Objects.equals(solution, that.solution) &&
                Objects.equals(mark, that.mark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lessonId, student, solution, mark);
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

    public HometaskSolution toHometaskSolution() {
        return new HometaskSolution(
                this.getId(),
                this.getLessonId(),
                this.getStudent(),
                this.getSolution()
        );
    }
}
