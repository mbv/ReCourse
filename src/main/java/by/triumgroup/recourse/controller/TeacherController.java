package by.triumgroup.recourse.controller;

import by.triumgroup.recourse.entity.model.Course;
import by.triumgroup.recourse.entity.model.Lesson;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public interface TeacherController {

    @GetMapping("{teacherId}/courses")
    List<Course> getCourses(
            @PathVariable("teacherId") Integer teacherId,
            @RequestParam(value = "status", required = false) Course.Status status,
            Pageable pageable
    );

    @GetMapping("{teacherId}/lessons")
    List<Lesson> getLessons(
            @PathVariable("teacherId") Integer teacherId,
            @RequestParam(value = "courseId", required = false) Integer courseId,
            Pageable pageable
    );

}
