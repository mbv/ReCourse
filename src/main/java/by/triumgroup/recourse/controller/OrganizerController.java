package by.triumgroup.recourse.controller;

import by.triumgroup.recourse.entity.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organizer")
public interface OrganizerController {

    @GetMapping("{organizerId}/courses")
    List<Course> getCourses(
            @PathVariable("organizerId") Integer organizerId,
            @RequestParam(value = "status", required = false) Course.Status status,
            Pageable pageable
    );


}
