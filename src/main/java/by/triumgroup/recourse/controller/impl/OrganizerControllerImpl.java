package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.OrganizerController;
import by.triumgroup.recourse.entity.Course;
import by.triumgroup.recourse.service.CourseService;
import org.slf4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static by.triumgroup.recourse.util.ServiceCallWrapper.wrapServiceCall;
import static org.slf4j.LoggerFactory.getLogger;

public class OrganizerControllerImpl implements OrganizerController {

    private static final Logger logger = getLogger(OrganizerControllerImpl.class);
    private final CourseService courseService;

    public OrganizerControllerImpl(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public List<Course> getCourses(
            @PathVariable("organizerId") Integer organizerId,
            @RequestParam(value = "status", required = false) Course.Status status,
            Pageable pageable) {
        return wrapServiceCall(logger, () -> {
            List<Course> courses;
            if (status == null) {
                courses = courseService.findByOrganizerId(organizerId, pageable);
            } else {
                courses = courseService.findByOrganizerIdAndStatus(organizerId, status, pageable);
            }
            return courses;
        });
    }
}
