package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.CrudController;
import by.triumgroup.recourse.controller.CrudControllerTest;
import by.triumgroup.recourse.controller.HometaskSolutionController;
import by.triumgroup.recourse.entity.model.HometaskSolution;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.service.HometaskSolutionService;
import by.triumgroup.recourse.service.LessonService;
import by.triumgroup.recourse.service.MarkService;
import by.triumgroup.recourse.supplier.entity.model.EntitySupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.HometaskSolutionSupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.MarkSupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.UserSupplier;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static by.triumgroup.recourse.util.Util.allItemsPage;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class HometaskSolutionControllerTest extends CrudControllerTest<HometaskSolution, Integer> {
    private static final String MARK_REQUEST = "/api/hometasks/solutions/1/mark";
    private static final String STUDENT_ID_REQUEST = "/api/hometasks/solutions/student/{id}";
    private static final String STUDENT_ID_REQUEST_PARAMS = "/api/hometasks/solutions/student/{id}?{name}={value}";
    private HometaskSolutionController hometaskSolutionController;
    private HometaskSolutionService hometaskSolutionService;
    private HometaskSolutionSupplier hometaskSolutionSupplier;
    private MarkService markService;
    private MarkSupplier markSupplier;
    private UserSupplier userSupplier;

    public HometaskSolutionControllerTest() {
        markService = Mockito.mock(MarkService.class);
        hometaskSolutionService = Mockito.mock(HometaskSolutionService.class);
        LessonService lessonService = Mockito.mock(LessonService.class);
        hometaskSolutionController = new HometaskSolutionControllerImpl(hometaskSolutionService, markService, lessonService);
        hometaskSolutionSupplier = new HometaskSolutionSupplier();
        markSupplier = new MarkSupplier();
        userSupplier = new UserSupplier();
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

    @Test
    @Override
    public void getAllEntitiesTest() throws Exception {
        when(getService().findAll(allItemsPage())).thenReturn(Lists.emptyList());
        User admin = userSupplier.getWithRole(User.Role.ADMIN);
        sendGet(generalRequest, admin)
                .andExpect(status().isOk());
    }

    public void getSolutionsExistingStudentTest() throws Exception {
        when(hometaskSolutionService.findByStudentId(any(), any())).thenReturn(Optional.of(Lists.emptyList()));
        User student = userSupplier.getWithRole(User.Role.STUDENT);
        sendGet(STUDENT_ID_REQUEST, student, student.getId())
                .andExpect(status().isOk());
    }

    @Test
    public void getSolutionsNotExistingStudentTest() throws Exception {
        when(hometaskSolutionService.findByStudentId(any(), any())).thenReturn(Optional.empty());
        User teacher = userSupplier.getWithRole(User.Role.TEACHER);
        sendGet(STUDENT_ID_REQUEST, teacher, teacher.getId())
                .andExpect(status().isNotFound());
    }

    @Test
    public void getSolutionForLessonTest() throws Exception {
        HometaskSolution hometaskSolution = hometaskSolutionSupplier.getValidEntityWithId();
        when(hometaskSolutionService.findByStudentIdAndLessonId(any(), any())).thenReturn(Optional.of(hometaskSolution));
        User student = prepareAuthorizedUser(hometaskSolution, userSupplier.getValidEntityWithId());
        sendGet(STUDENT_ID_REQUEST_PARAMS, student, student.getId(), "lessonId", 1)
                .andExpect(status().isOk());
    }

    @Test
    public void getSolutionForLessonNotExistingTest() throws Exception {
        when(hometaskSolutionService.findByStudentIdAndLessonId(any(), any())).thenReturn(Optional.empty());
        User student = userSupplier.getWithRole(User.Role.STUDENT);
        sendGet(STUDENT_ID_REQUEST_PARAMS, student, student.getId(), "lessonId", 1)
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
        return "hometasks/solutions";
    }

    @Override
    protected EntitySupplier<HometaskSolution, Integer> getEntitySupplier() {
        return hometaskSolutionSupplier;
    }

    @Override
    protected User prepareAuthorizedUser(HometaskSolution entity, User validUserWithId) {
        validUserWithId.setId(entity.getStudent().getId());
        return validUserWithId;
    }
}
