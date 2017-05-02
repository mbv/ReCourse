package by.triumgroup.recourse.controller;

import by.triumgroup.recourse.controller.exception.ControllerException;
import by.triumgroup.recourse.entity.dto.MarkedHometaskSolution;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/hometasks/solutions/marked")
public interface MarkedHometaskSolutionController {

    @GetMapping("{id}")
    MarkedHometaskSolution getById(@PathVariable("id") Integer integer) throws ControllerException;

    @GetMapping
    Iterable<MarkedHometaskSolution> getAll(Pageable pageable) throws ControllerException;

    @PostMapping
    MarkedHometaskSolution create(@Valid @RequestBody MarkedHometaskSolution entity) throws ControllerException;

    @PutMapping("{id}")
    MarkedHometaskSolution update(@Valid @RequestBody MarkedHometaskSolution entity, @PathVariable("id") Integer integer) throws ControllerException;

    @DeleteMapping("{id}")
    void delete(@PathVariable("id") Integer integer) throws ControllerException;

    @GetMapping(value = "/student/{studentId}", params = "lessonId")
    MarkedHometaskSolution getStudentSolution(
            @PathVariable("studentId") Integer studentId,
            @RequestParam(value = "lessonId") Integer lessonId);

    @GetMapping("/student/{studentId}")
    List<MarkedHometaskSolution> getStudentSolutions(
            @PathVariable("studentId") Integer studentId,
            Pageable pageable);

    @GetMapping("/lesson/{lessonId}")
    List<MarkedHometaskSolution> getLessonSolutions(
            @PathVariable("lessonId") Integer lessonId,
            Pageable pageable);


}
