package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.AbstractControllerTest;
import by.triumgroup.recourse.controller.OrganizerController;
import by.triumgroup.recourse.entity.model.Course;
import by.triumgroup.recourse.service.CourseService;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrganizerControllerTest extends AbstractControllerTest {
    private OrganizerController organizerController;
    private CourseService courseService;
    private static final String REQUEST_WITHOUT_STATUS = "/organizer/1/courses";
    private static final String REQUEST_WITH_STATUS = REQUEST_WITHOUT_STATUS + "?status={status}";

    public OrganizerControllerTest() {
        courseService = Mockito.mock(CourseService.class);
        organizerController = new OrganizerControllerImpl(courseService);
    }

    @Test
    public void getCoursesExistingOrganizerTest() throws Exception {
        when(courseService.findByOrganizerId(any(), any())).thenReturn(Optional.of(Lists.emptyList()));
        sendGet(REQUEST_WITHOUT_STATUS)
                .andExpect(status().isOk());
    }

    @Test
    public void getCoursesByStatusExistingOrganizerTest() throws Exception {
        when(courseService.findByOrganizerIdAndStatus(any(), any(), any())).thenReturn(Optional.of(Lists.emptyList()));
        sendGet(REQUEST_WITH_STATUS, Course.Status.ONGOING)
                .andExpect(status().isOk());
    }

    @Test
    public void getCoursesNotExistingOrganizerTest() throws Exception {
        when(courseService.findByOrganizerId(any(), any())).thenReturn(Optional.empty());
        sendGet(REQUEST_WITHOUT_STATUS)
                .andExpect(status().isNotFound());
    }

    @Test
    public void getCoursesInvalidStatusTest() throws Exception {
        when(courseService.findByOrganizerId(any(), any())).thenReturn(Optional.of(Lists.emptyList()));
        sendGet(REQUEST_WITH_STATUS, "NOT_A_VALID_STATUS")
                .andExpect(status().isBadRequest());
    }

    @Override
    protected Object getController() {
        return organizerController;
    }
}
