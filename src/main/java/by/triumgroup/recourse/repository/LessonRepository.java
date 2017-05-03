package by.triumgroup.recourse.repository;

import by.triumgroup.recourse.entity.model.Lesson;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public interface LessonRepository extends PagingAndSortingRepository<Lesson, Integer> {

    List<Lesson> findByCourseIdOrderByStartTimeDesc(Integer id, Pageable pageable);

    List<Lesson> findByTeacherIdOrderByStartTimeDesc(Integer id, Pageable pageable);

    List<Lesson> findByTeacherIdAndCourseIdOrderByStartTimeDesc(Integer teacherId, Integer courseId, Pageable pageable);

    @Query(value = "SELECT recourse.can_add_lesson(:teacher_id, :new_lesson_start_time, :duration)", nativeQuery = true)
    boolean canAddLesson(@Param("new_lesson_start_time") Timestamp startTime,
                         @Param("teacher_id") Integer teacherId,
                         @Param("duration") Time duration);

    @Query(value = "SELECT recourse.can_update_lesson(:teacher_id, :new_lesson_start_time, :duration, :lesson_id)", nativeQuery = true)
    boolean canUpdateLesson(@Param("new_lesson_start_time") Timestamp startTime,
                            @Param("teacher_id") Integer teacherId,
                            @Param("duration") Time duration,
                            @Param("lesson_id") Integer lessonId);

    @Query(value = "SELECT lesson.id, lesson.start_time, lesson.duration, lesson.course_id, lesson.topic, lesson.teacher_id, lesson.task\n" +
            "FROM lesson\n" +
            "JOIN course JOIN course_student\n" +
            "ON ((course.id = course_student.course_id) and\n" +
            "(course_student.student_id = ?1) and\n" +
            "(course.status = 'PUBLISHED') and\n" +
            "(lesson.course_id = course.id) and\n" +
            "(lesson.start_time > now()))\n" +
            "ORDER BY lesson.start_time ASC\n" +
            "#pageable\n",
            nativeQuery = true)
    List<Lesson> findFutureLessonsByUserId(Integer userId, Pageable pageable);

    @Query(value = "SELECT lesson.id, lesson.start_time, lesson.duration, lesson.course_id, lesson.topic, lesson.teacher_id, lesson.task\n" +
            "FROM lesson\n" +
            "JOIN course JOIN course_student\n" +
            "ON ((course.id = course_student.course_id) and\n" +
            "(course_student.student_id = ?1) and\n" +
            "(course.status = 'PUBLISHED') and\n" +
            "(lesson.course_id = course.id) and\n" +
            "(lesson.start_time <= now()))\n" +
            "ORDER BY lesson.start_time ASC\n" +
            "#pageable\n",
            nativeQuery = true)
    List<Lesson> findPastLessonsByUserId(Integer userId, Pageable pageable);
}