package by.triumgroup.recourse.controller;

import by.triumgroup.recourse.entity.model.HometaskSolution;
import by.triumgroup.recourse.entity.model.StudentReport;
import by.triumgroup.recourse.entity.model.TeacherFeedback;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public interface StudentController {

    @GetMapping("{studentId}/reports")
    List<StudentReport> getReports(
            @PathVariable("studentId") Integer studentId,
            Pageable pageable
    );

    @GetMapping("{studentId}/feedbacks")
    List<TeacherFeedback> getTeacherFeedbacks(
            @PathVariable("studentId") Integer studentId,
            Pageable pageable
    );

    @GetMapping("{studentId}/solutions")
    List<HometaskSolution> getSolutions(
            @PathVariable("studentId") Integer studentId,
            Pageable pageable
    );

    @GetMapping("{studentId}/solution")
    HometaskSolution getSolutionForLesson(
            @PathVariable("studentId") Integer studentId,
            @RequestParam("hometaskId") Integer hometaskId,
            Pageable pageable
    );

}
