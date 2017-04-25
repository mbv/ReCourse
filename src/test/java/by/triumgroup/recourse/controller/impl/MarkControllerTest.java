package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.CrudController;
import by.triumgroup.recourse.controller.CrudControllerTest;
import by.triumgroup.recourse.controller.MarkController;
import by.triumgroup.recourse.entity.model.HometaskSolution;
import by.triumgroup.recourse.entity.model.Lesson;
import by.triumgroup.recourse.entity.model.Mark;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.service.HometaskSolutionService;
import by.triumgroup.recourse.service.LessonService;
import by.triumgroup.recourse.service.MarkService;
import by.triumgroup.recourse.supplier.entity.model.EntitySupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.HometaskSolutionSupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.LessonSupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.MarkSupplier;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.when;

public class MarkControllerTest extends CrudControllerTest<Mark, Integer> {
    private LessonService lessonService;
    private HometaskSolutionService hometaskSolutionService;
    private MarkController markController;
    private MarkService markService;
    private MarkSupplier markSupplier;
    private HometaskSolutionSupplier hometaskSolutionSupplier;
    private LessonSupplier lessonSupplier;

    public MarkControllerTest() {
        markService = Mockito.mock(MarkService.class);
        lessonService = Mockito.mock(LessonService.class);
        hometaskSolutionService = Mockito.mock(HometaskSolutionService.class);
        markController = new MarkControllerImpl(markService, hometaskSolutionService, lessonService);
        markSupplier = new MarkSupplier();
        hometaskSolutionSupplier = new HometaskSolutionSupplier();
        lessonSupplier = new LessonSupplier();
    }

    @Override
    protected CrudController<Mark, Integer> getController() {
        return markController;
    }

    @Override
    protected CrudService<Mark, Integer> getService() {
        return markService;
    }

    @Override
    protected String getEntityName() {
        return "hometask/solution/mark";
    }

    @Override
    protected EntitySupplier<Mark, Integer> getEntitySupplier() {
        return markSupplier;
    }

    @Override
    protected User prepareAuthorizedUser(Mark entity, User validUserWithId) {
        HometaskSolution hometaskSolution = hometaskSolutionSupplier.getValidEntityWithId();
        when(hometaskSolutionService.findById(entity.getSolutionId())).thenReturn(Optional.of(hometaskSolution));
        Lesson lesson = lessonSupplier.getValidEntityWithId();
        validUserWithId.setId(lesson.getTeacher().getId());
        when(lessonService.findById(hometaskSolution.getLessonId())).thenReturn(Optional.of(lesson));
        return validUserWithId;
    }
}
