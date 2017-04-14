package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.CrudController;
import by.triumgroup.recourse.controller.CrudControllerTest;
import by.triumgroup.recourse.controller.HometaskSolutionController;
import by.triumgroup.recourse.entity.model.HometaskSolution;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.service.HometaskSolutionService;
import by.triumgroup.recourse.service.MarkService;
import by.triumgroup.recourse.supplier.entity.model.EntitySupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.HometaskSolutionSupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.MarkSupplier;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class HometaskSolutionControllerTest extends CrudControllerTest<HometaskSolution, Integer> {
    private static final String MARK_REQUEST = "/hometask/solution/1/mark";
    private HometaskSolutionController hometaskSolutionController;
    private HometaskSolutionService hometaskSolutionService;
    private HometaskSolutionSupplier hometaskSolutionSupplier;
    private MarkService markService;
    private MarkSupplier markSupplier;

    public HometaskSolutionControllerTest() {
        markService = Mockito.mock(MarkService.class);
        hometaskSolutionService = Mockito.mock(HometaskSolutionService.class);
        hometaskSolutionController = new HometaskSolutionControllerImpl(hometaskSolutionService, markService);
        hometaskSolutionSupplier = new HometaskSolutionSupplier();
        markSupplier = new MarkSupplier();
    }

    @Test
    public void getMarkExistingSolutionTest() throws Exception {
        when(markService.findBySolutionId(any())).thenReturn(Optional.of(markSupplier.getValidEntityWithId()));
        sendGet(MARK_REQUEST)
                .andExpect(status().isOk());
    }

    @Test
    public void getMarkNotExistingSolutionTest() throws Exception {
        when(markService.findBySolutionId(any())).thenReturn(Optional.empty());
        sendGet(MARK_REQUEST)
                .andExpect(status().isNotFound());
    }

    @Override
    protected CrudController<HometaskSolution, Integer> getController() {
        return hometaskSolutionController;
    }

    @Override
    protected CrudService<HometaskSolution, Integer> getService() {
        return hometaskSolutionService;
    }

    @Override
    protected String getEntityName() {
        return "hometask/solution";
    }

    @Override
    protected EntitySupplier<HometaskSolution, Integer> getEntitySupplier() {
        return hometaskSolutionSupplier;
    }
}
