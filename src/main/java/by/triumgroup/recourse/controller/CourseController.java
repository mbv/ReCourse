package by.triumgroup.recourse.controller;

import by.triumgroup.recourse.entity.Course;
import by.triumgroup.recourse.entity.CourseFeedback;
import by.triumgroup.recourse.entity.Lesson;
import by.triumgroup.recourse.entity.StudentReport;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public interface CourseController extends CrudController<Course, Integer> {

    @GetMapping("{courseId}/lessons")
    List<Lesson> getLessons(
            @PathVariable("courseId") Integer courseId, Pageable pageable);

    @GetMapping("{courseId}/feedbacks")
    List<CourseFeedback> getFeedbacks(
            @PathVariable("courseId") Integer courseId, Pageable pageable);

    @GetMapping("{courseId}/reports")
    List<StudentReport> getReports(
            @PathVariable("courseId") Integer courseId, Pageable pageable);

    @GetMapping("search")
    List<Course> searchByTitle(@RequestParam("title") String title, Pageable pageable);

    @GetMapping("all")
    List<Course> getAll(
            @RequestParam(value = "status", required = false) Course.Status status,
            Pageable pageable
    );

}
