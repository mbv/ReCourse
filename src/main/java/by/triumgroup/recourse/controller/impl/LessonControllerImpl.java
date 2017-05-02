package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.configuration.security.Auth;
import by.triumgroup.recourse.configuration.security.UserAuthDetails;
import by.triumgroup.recourse.controller.LessonController;
import by.triumgroup.recourse.controller.exception.AccessDeniedException;
import by.triumgroup.recourse.controller.exception.BadRequestException;
import by.triumgroup.recourse.controller.exception.NotFoundException;
import by.triumgroup.recourse.entity.model.Lesson;
import by.triumgroup.recourse.service.LessonService;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Optional;

import static by.triumgroup.recourse.util.ServiceCallWrapper.wrapServiceCall;
import static org.slf4j.LoggerFactory.getLogger;

public class LessonControllerImpl
        extends AbstractCrudController<Lesson, Integer>
        implements LessonController {

    private static final Logger logger = getLogger(LessonControllerImpl.class);
    private LessonService lessonService;

    public LessonControllerImpl(LessonService lessonService) {
        super(lessonService, logger);
        this.lessonService = lessonService;
    }

    @Override
    protected void validateNestedEntities(Lesson entity) {
        if (entity.getTeacher().getId() == null) {
            throw new BadRequestException("teacher.id", "Teacher ID is not specified");
        }
    }

    @Override
    public Lesson update(@Valid @RequestBody Lesson entity, @PathVariable("id") Integer id, @Auth UserAuthDetails authDetails) {
        validateNestedEntities(entity);
        if (!authDetails.isAdmin() && !(authDetails.getId().equals(entity.getTeacher().getId()))) {
            throw new AccessDeniedException();
        }
        return wrapServiceCall(logger, () -> {
            Optional<Lesson> callResult = lessonService.update(entity, id, authDetails.getRole());
            return callResult.orElseThrow(NotFoundException::new);
        });
    }

    @Override
    protected boolean hasAuthorityToEdit(Lesson entity, UserAuthDetails authDetails) {
        return false;
    }
}
