package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.CrudController;
import by.triumgroup.recourse.controller.CrudControllerTest;
import by.triumgroup.recourse.controller.LessonController;
import by.triumgroup.recourse.entity.model.Lesson;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.service.HometaskService;
import by.triumgroup.recourse.service.LessonService;
import by.triumgroup.recourse.supplier.entity.model.EntitySupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.HometaskSupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.LessonSupplier;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LessonControllerTest extends CrudControllerTest<Lesson, Integer> {

    private static final String HOMETASK_REQUEST = "/lesson/1/hometask";
    private LessonController lessonController;
    private LessonService lessonService;
    private LessonSupplier lessonSupplier;
    private HometaskService hometaskService;
    private HometaskSupplier hometaskSupplier;

    public LessonControllerTest() {
        lessonService = Mockito.mock(LessonService.class);
        hometaskService = Mockito.mock(HometaskService.class);
        lessonController = new LessonControllerImpl(lessonService, hometaskService);
        lessonSupplier = new LessonSupplier();
        hometaskSupplier = new HometaskSupplier();
    }

    @Test
    public void getHometaskExistingLessonTest() throws Exception {
        when(hometaskService.findByLessonId(any())).thenReturn(Optional.of(hometaskSupplier.getValidEntityWithId()));
        sendGet(HOMETASK_REQUEST)
                .andExpect(status().isOk());
    }

    @Test
    public void getHometaskNotExistingLessonTest() throws Exception {
        when(hometaskService.findByLessonId(any())).thenReturn(Optional.empty());
        sendGet(HOMETASK_REQUEST)
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
        return "lesson";
    }

    @Override
    protected EntitySupplier<Lesson, Integer> getEntitySupplier() {
        return lessonSupplier;
    }
}
