package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.StudentController;
import by.triumgroup.recourse.controller.exception.NotFoundException;
import by.triumgroup.recourse.entity.model.HometaskSolution;
import by.triumgroup.recourse.service.HometaskSolutionService;
import org.slf4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

import static by.triumgroup.recourse.util.ServiceCallWrapper.wrapServiceCall;
import static org.slf4j.LoggerFactory.getLogger;

public class StudentControllerImpl implements StudentController {

    private static final Logger logger = getLogger(StudentControllerImpl.class);
    private final HometaskSolutionService hometaskSolutionService;

    public StudentControllerImpl(HometaskSolutionService hometaskSolutionService) {
        this.hometaskSolutionService = hometaskSolutionService;
    }

    @Override
    public List<HometaskSolution> getSolutions(@PathVariable("studentId") Integer studentId, Pageable pageable) {
        return wrapServiceCall(logger, () -> {
            Optional<List<HometaskSolution>> solutions = hometaskSolutionService.findByStudentId(studentId, pageable);
            return solutions.orElseThrow(NotFoundException::new);
        });
    }

    @Override
    public HometaskSolution getSolutionForLesson(
            @PathVariable("studentId") Integer studentId,
            @RequestParam("hometaskId") Integer hometaskId,
            Pageable pageable) {
        return wrapServiceCall(logger, () -> {
            Optional<HometaskSolution> callResult =
                    hometaskSolutionService.findByStudentIdAndLessonId(studentId, hometaskId);
            return callResult.orElseThrow(NotFoundException::new);
        });
    }
}
