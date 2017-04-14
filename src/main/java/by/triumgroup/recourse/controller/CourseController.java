package by.triumgroup.recourse.controller;

import by.triumgroup.recourse.entity.model.Course;
import by.triumgroup.recourse.entity.model.CourseFeedback;
import by.triumgroup.recourse.entity.model.Lesson;
import by.triumgroup.recourse.entity.model.StudentReport;
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

    @GetMapping(value = "search", params = "title")
    List<Course> searchByTitle(@RequestParam("title") String title, Pageable pageable);

    @GetMapping(value = "search", params = "status")
    List<Course> searchByStatus(@RequestParam("status") Course.Status status, Pageable pageable);

}
