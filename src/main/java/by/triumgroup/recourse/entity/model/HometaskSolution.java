package by.triumgroup.recourse.entity.model;

import by.triumgroup.recourse.entity.dto.MarkedHometaskSolution;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@SqlResultSetMapping(
        name = "MarkedHometaskSolutionMapping",
        classes = @ConstructorResult(
                targetClass = MarkedHometaskSolution.class,
                columns = {
                        @ColumnResult(name = "solutionId", type = Integer.class),
                        @ColumnResult(name = "solutionLessonId", type = Integer.class),
                        @ColumnResult(name = "solutionSolution", type = String.class),
                        @ColumnResult(name = "studentId", type = Integer.class),
                        @ColumnResult(name = "studentEmail", type = String.class),
                        @ColumnResult(name = "studentName", type = String.class),
                        @ColumnResult(name = "studentSurname", type = String.class),
                        @ColumnResult(name = "studentGender", type = String.class),
                        @ColumnResult(name = "studentBirthday", type = String.class),
                        @ColumnResult(name = "studentRole", type = String.class),
                        @ColumnResult(name = "markId", type = Integer.class),
                        @ColumnResult(name = "markScore", type = Integer.class),
                        @ColumnResult(name = "markComment", type = String.class),
                }
        )
)
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "HometaskSolution.findAllMarked",
                query = "SELECT " +
                        "   s.id AS solutionId, " +
                        "   s.lesson_id AS solutionLessonId, " +
                        "   s.solution AS solutionSolution, " +
                        "   u.id AS studentId," +
                        "   u.email AS studentEmail," +
                        "   u.name AS studentName," +
                        "   u.surname AS studentSurname, " +
                        "   u.gender AS studentGender," +
                        "   u.birthday AS studentBirthday, " +
                        "   u.role AS studentRole, " +
                        "   m.id AS markId, " +
                        "   m.score AS markScore, " +
                        "   m.comment AS markComment " +
                        "FROM hometask_solution AS s " +
                        "   JOIN user AS u ON s.student_id = u.id" +
                        "   LEFT JOIN mark AS m ON s.id = m.solution_id " +
                        "ORDER BY s.id DESC ",
                resultSetMapping = "MarkedHometaskSolutionMapping"
        ),
        @NamedNativeQuery(
                name = "HometaskSolution.findMarkedByStudentId",
                query = "SELECT " +
                        "   s.id AS solutionId, " +
                        "   s.lesson_id AS solutionLessonId, " +
                        "   s.solution AS solutionSolution, " +
                        "   u.id AS studentId," +
                        "   u.email AS studentEmail," +
                        "   u.name AS studentName," +
                        "   u.surname AS studentSurname, " +
                        "   u.gender AS studentGender," +
                        "   u.birthday AS studentBirthday, " +
                        "   u.role AS studentRole, " +
                        "   m.id AS markId, " +
                        "   m.score AS markScore, " +
                        "   m.comment AS markComment " +
                        "FROM hometask_solution AS s " +
                        "   JOIN user AS u ON s.student_id = u.id" +
                        "   LEFT JOIN mark AS m ON s.id = m.solution_id " +
                        "WHERE s.student_id = :studentId " +
                        "ORDER BY s.id DESC ",
                resultSetMapping = "MarkedHometaskSolutionMapping"
        ),
        @NamedNativeQuery(
                name = "HometaskSolution.findMarkedByLessonId",
                query = "SELECT " +
                        "   s.id AS solutionId, " +
                        "   s.lesson_id AS solutionLessonId, " +
                        "   s.solution AS solutionSolution, " +
                        "   u.id AS studentId," +
                        "   u.email AS studentEmail," +
                        "   u.name AS studentName," +
                        "   u.surname AS studentSurname, " +
                        "   u.gender AS studentGender," +
                        "   u.birthday AS studentBirthday, " +
                        "   u.role AS studentRole, " +
                        "   m.id AS markId, " +
                        "   m.score AS markScore, " +
                        "   m.comment AS markComment " +
                        "FROM hometask_solution AS s " +
                        "   JOIN user AS u ON s.student_id = u.id" +
                        "   LEFT JOIN mark AS m ON s.id = m.solution_id " +
                        "WHERE s.lesson_id = :lessonId " +
                        "ORDER BY s.id DESC ",
                resultSetMapping = "MarkedHometaskSolutionMapping"
        )
})
@Table(name = "hometask_solution")
public class HometaskSolution extends BaseEntity<Integer> {

    @NotNull(message = "Lesson ID is not specified")
    @Column(columnDefinition = "INT(11)", nullable = false)
    private Integer lessonId;

    @NotNull(message = "Student is not specified")
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "student_id")
    private User student;

    @NotNull(message = "Solution is not specified")
    @SafeHtml
    @Size(min = 1, max = 2000, message = "Solution length must be in range 1-2000")
    @Column(columnDefinition = "TEXT", nullable = false)
    private String solution;


    public HometaskSolution() {
    }

    public HometaskSolution(Integer id, Integer lessonId, User student, String solution) {
        super(id);
        this.lessonId = lessonId;
        this.student = student;
        this.solution = solution;
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

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        HometaskSolution that = (HometaskSolution) o;
        return Objects.equals(lessonId, that.lessonId) &&
                Objects.equals(student, that.student) &&
                Objects.equals(solution, that.solution);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), lessonId, student, solution);
    }
}
