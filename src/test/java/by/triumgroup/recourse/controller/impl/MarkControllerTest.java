package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.CrudController;
import by.triumgroup.recourse.controller.CrudControllerTest;
import by.triumgroup.recourse.controller.MarkController;
import by.triumgroup.recourse.entity.model.Mark;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.service.MarkService;
import by.triumgroup.recourse.supplier.entity.model.EntitySupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.MarkSupplier;
import org.mockito.Mockito;

public class MarkControllerTest extends CrudControllerTest<Mark, Integer> {
    private MarkController markController;
    private MarkService markService;
    private MarkSupplier markSupplier;

    public MarkControllerTest() {
        markService = Mockito.mock(MarkService.class);
        markController = new MarkControllerImpl(markService);
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
