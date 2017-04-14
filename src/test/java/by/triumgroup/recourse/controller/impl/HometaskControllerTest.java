package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.CrudController;
import by.triumgroup.recourse.controller.CrudControllerTest;
import by.triumgroup.recourse.controller.HometaskController;
import by.triumgroup.recourse.entity.model.Hometask;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.service.HometaskService;
import by.triumgroup.recourse.service.HometaskSolutionService;
import by.triumgroup.recourse.supplier.entity.model.EntitySupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.HometaskSupplier;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class HometaskControllerTest extends CrudControllerTest<Hometask, Integer> {
    private static final String SOLUTIONS_REQUEST = "/hometask/1/solutions";
    private HometaskController hometaskController;
    private HometaskService hometaskService;
    private HometaskSupplier hometaskSupplier;
    private HometaskSolutionService hometaskSolutionService;

    public HometaskControllerTest() {
        hometaskService = Mockito.mock(HometaskService.class);
        hometaskSolutionService = Mockito.mock(HometaskSolutionService.class);
        hometaskController = new HometaskControllerImpl(hometaskService, hometaskSolutionService);
        hometaskSupplier = new HometaskSupplier();
    }

    @Test
    public void getSolutionsExistingHometaskTest() throws Exception {
        when(hometaskSolutionService.findByHometaskId(any(), any())).thenReturn(Optional.of(Lists.emptyList()));
        sendGet(SOLUTIONS_REQUEST)
                .andExpect(status().isOk());
    }

    @Test
    public void getSolutionsNotExistingHometaskTest() throws Exception {
        when(hometaskSolutionService.findByHometaskId(any(), any())).thenReturn(Optional.empty());
        sendGet(SOLUTIONS_REQUEST)
                .andExpect(status().isNotFound());
    }

    @Override
    protected CrudController<Hometask, Integer> getController() {
        return hometaskController;
    }

    @Override
    protected CrudService<Hometask, Integer> getService() {
        return hometaskService;
    }

    @Override
    protected String getEntityName() {
        return "hometask";
    }

    @Override
    protected EntitySupplier<Hometask, Integer> getEntitySupplier() {
        return hometaskSupplier;
    }
}
