package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.MarkedHometaskSolutionController;
import by.triumgroup.recourse.controller.exception.BadRequestException;
import by.triumgroup.recourse.controller.exception.ControllerException;
import by.triumgroup.recourse.controller.exception.NotFoundException;
import by.triumgroup.recourse.entity.dto.ErrorMessage;
import by.triumgroup.recourse.entity.dto.MarkedHometaskSolution;
import by.triumgroup.recourse.entity.model.HometaskSolution;
import by.triumgroup.recourse.entity.model.Mark;
import by.triumgroup.recourse.service.HometaskSolutionService;
import by.triumgroup.recourse.service.MarkService;
import org.slf4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.triumgroup.recourse.util.ServiceCallWrapper.wrapServiceCall;
import static org.slf4j.LoggerFactory.getLogger;

public class MarkedHometaskSolutionControllerImpl implements MarkedHometaskSolutionController {

    private static final Logger logger = getLogger(MarkedHometaskSolutionControllerImpl.class);
    private final HometaskSolutionService hometaskSolutionService;
    private final MarkService markService;

    public MarkedHometaskSolutionControllerImpl(
            HometaskSolutionService hometaskSolutionService,
            MarkService markService) {
        this.hometaskSolutionService = hometaskSolutionService;
        this.markService = markService;
    }

    @Override
    public MarkedHometaskSolution getById(@PathVariable("id") Integer id) throws ControllerException {
        return wrapServiceCall(logger, () -> {
            Optional<HometaskSolution> hometaskSolution = hometaskSolutionService.findById(id);
            if (hometaskSolution.isPresent()) {
                Optional<Mark> mark = markService.findBySolutionId(hometaskSolution.get().getId());
                return MarkedHometaskSolution.from(hometaskSolution.get(), mark.orElse(null));
            } else {
                throw new NotFoundException();
            }
        });
    }

    @Override
    public Iterable<MarkedHometaskSolution> getAll(Pageable pageable) throws ControllerException {
        return wrapServiceCall(logger, () -> hometaskSolutionService.findAllMarked(pageable));
    }

    @Override
    public MarkedHometaskSolution create(@Valid @RequestBody MarkedHometaskSolution entity) throws ControllerException {
        validateNestedEntities(entity);
        return wrapServiceCall(logger, () -> {
            Optional<HometaskSolution> hometaskSolution = hometaskSolutionService.add(entity.toHometaskSolution());
            if (hometaskSolution.isPresent()) {
                MarkedHometaskSolution result;
                if (entity.getMark() != null) {
                    entity.getMark().setSolutionId(entity.getId());
                    Optional<Mark> mark = markService.add(entity.getMark());
                    result = MarkedHometaskSolution.from(hometaskSolution.get(), mark.orElseThrow(BadRequestException::new));
                } else {
                    result = MarkedHometaskSolution.from(hometaskSolution.get(), null);
                }
                return result;
            } else {
                throw new BadRequestException();
            }
        });
    }

    @Override
    public MarkedHometaskSolution update(@Valid @RequestBody MarkedHometaskSolution entity, @PathVariable("id") Integer id) throws ControllerException {
        validateNestedEntities(entity);
        return wrapServiceCall(logger, () -> {
            Optional<HometaskSolution> hometaskSolution = hometaskSolutionService.update(entity.toHometaskSolution(), id);
            if (hometaskSolution.isPresent()) {
                MarkedHometaskSolution result;
                if (entity.getMark() != null) {
                    entity.getMark().setSolutionId(entity.getId());
                    Optional<Mark> storedMark = markService.findBySolutionId(id);
                    Optional<Mark> mark;
                    if (storedMark.isPresent()) {
                        entity.getMark().setId(storedMark.get().getId());
                        mark = markService.update(entity.getMark(), entity.getMark().getId());
                    } else {
                        mark = markService.add(entity.getMark());
                    }
                    result = MarkedHometaskSolution.from(hometaskSolution.get(), mark.orElseThrow(BadRequestException::new));
                } else {
                    result = MarkedHometaskSolution.from(hometaskSolution.get(), null);
                }
                return result;
            } else {
                throw new BadRequestException();
            }
        });
    }

    @Override
    public void delete(@PathVariable("id") Integer id) throws ControllerException {
        wrapServiceCall(logger, () -> {
            Optional<Mark> storedMark = markService.findBySolutionId(id);
            storedMark.ifPresent(mark -> markService.delete(mark.getId()).orElseThrow(BadRequestException::new));
            hometaskSolutionService.delete(id).orElseThrow(BadRequestException::new);
        });
    }

    @Override
    public MarkedHometaskSolution getStudentSolution(
            @PathVariable("studentId") Integer studentId,
            @RequestParam(value = "lessonId") Integer lessonId) {
        return wrapServiceCall(logger, () -> {
            Optional<HometaskSolution> hometaskSolution = hometaskSolutionService.findByStudentIdAndLessonId(studentId, lessonId);
            if (hometaskSolution.isPresent()) {
                Optional<Mark> mark = markService.findBySolutionId(hometaskSolution.get().getId());
                return MarkedHometaskSolution.from(hometaskSolution.get(), mark.orElse(null));
            } else {
                throw new NotFoundException();
            }
        });
    }

    @Override
    public List<MarkedHometaskSolution> getStudentSolutions(
            @PathVariable("studentId") Integer studentId,
            Pageable pageable) {
        return wrapServiceCall(logger, () -> hometaskSolutionService.findMarkedByStudentId(studentId, pageable));
    }

    @Override
    public List<MarkedHometaskSolution> getLessonSolutions(
            @PathVariable("lessonId") Integer lessonId,
            Pageable pageable) {
        return wrapServiceCall(logger, () -> hometaskSolutionService.findMarkedByLessonId(lessonId, pageable));
    }

    private void validateNestedEntities(MarkedHometaskSolution hometaskSolution) {
        List<ErrorMessage> messages = new ArrayList<>();
        if (hometaskSolution.getStudent().getId() == null) {
            messages.add(new ErrorMessage("student.id", "Student ID is not specified"));
        }
        if (!messages.isEmpty()) {
            throw new BadRequestException(messages);
        }
    }

}
