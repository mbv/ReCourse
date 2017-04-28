package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.CrudController;
import by.triumgroup.recourse.controller.CrudControllerTest;
import by.triumgroup.recourse.controller.LessonController;
import by.triumgroup.recourse.entity.model.Lesson;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.service.LessonService;
import by.triumgroup.recourse.supplier.entity.model.EntitySupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.LessonSupplier;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LessonControllerTest extends CrudControllerTest<Lesson, Integer> {

    private LessonController lessonController;
    private LessonService lessonService;
    private LessonSupplier lessonSupplier;

    public LessonControllerTest() {
        lessonService = Mockito.mock(LessonService.class);
        lessonController = new LessonControllerImpl(lessonService);
        lessonSupplier = new LessonSupplier();
    }

    @Override
    public void updateEntityValidDataTest() throws Exception {
        when(lessonService.update(any(), any(), any())).thenReturn(Optional.of(getEntitySupplier().getValidEntityWithId()));

        putEntityByIdAuthorized(getEntitySupplier().getAnyId(), getEntitySupplier().getValidEntityWithoutId())
                .andExpect(status().isOk());
    }

    @Override
    public void updateNotExistingEntityTest() throws Exception {
        when(lessonService.update(any(), any(), any())).thenReturn(Optional.empty());

        putEntityByIdAuthorized(getEntitySupplier().getAnyId(), getEntitySupplier().getValidEntityWithoutId())
                .andExpect(status().isNotFound());
    }

    @Override
    protected CrudController<Lesson, Integer> getController() {
        return lessonController;
    }

    @Override
    protected CrudService<Lesson, Integer> getService() {
        return lessonService;
    }

    @Override
    protected String getEntityName() {
        return "lessons";
    }

    @Override
    protected EntitySupplier<Lesson, Integer> getEntitySupplier() {
        return lessonSupplier;
    }

    @Override
    protected User prepareAuthorizedUser(Lesson entity, User validUserWithId) {
        validUserWithId.setId(entity.getTeacher().getId());
        return validUserWithId;
    }
}
