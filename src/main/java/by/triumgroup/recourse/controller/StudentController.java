package by.triumgroup.recourse.controller;

import by.triumgroup.recourse.entity.model.Lesson;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/students")
public interface StudentController {

    @GetMapping("{studentId}/future_lessons")
    List<Lesson> getFutureForStudent(@PathVariable("studentId") Integer studentId, Pageable pageable);

    @GetMapping("{studentId}/past_lessons")
    List<Lesson> getPastForStudent(@PathVariable("studentId") Integer studentId, Pageable pageable);
}
