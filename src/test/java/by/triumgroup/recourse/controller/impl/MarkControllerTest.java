package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.CrudController;
import by.triumgroup.recourse.controller.CrudControllerTest;
import by.triumgroup.recourse.controller.MarkController;
import by.triumgroup.recourse.entity.model.Mark;
import by.triumgroup.recourse.service.*;
import by.triumgroup.recourse.supplier.entity.model.EntitySupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.MarkSupplier;
import org.mockito.Mockito;

public class MarkControllerTest extends CrudControllerTest<Mark, Integer> {
    private LessonService lessonService;
    private HometaskSolutionService hometaskSolutionService;
    private HometaskService hometaskService;
    private MarkController markController;
    private MarkService markService;
    private MarkSupplier markSupplier;

    public MarkControllerTest() {
        markService = Mockito.mock(MarkService.class);
        lessonService = Mockito.mock(LessonService.class);
        hometaskSolutionService = Mockito.mock(HometaskSolutionService.class);
        hometaskService = Mockito.mock(HometaskService.class);
        markController = new MarkControllerImpl(markService, hometaskSolutionService, hometaskService, lessonService);
        markSupplier = new MarkSupplier();
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
}
