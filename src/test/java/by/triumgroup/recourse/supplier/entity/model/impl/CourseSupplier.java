package by.triumgroup.recourse.supplier.entity.model.impl;

import by.triumgroup.recourse.entity.model.Course;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.supplier.entity.model.EntityIntegerPKSupplier;

import java.sql.Timestamp;

public class CourseSupplier implements EntityIntegerPKSupplier<Course> {
    private UserSupplier userSupplier = new UserSupplier();

    @Override
    public Course getValidEntityWithoutId() {
        Course course =  new Course();
        course.setTitle("Title");
        course.setDescription("Description");
        course.setMaxStudents(10);
        course.setStatus(Course.Status.DRAFT);
        course.setRegistrationEnd(new Timestamp(Long.MAX_VALUE));
        User teacher = userSupplier.getWithRole(User.Role.TEACHER);
        teacher.setId(teacher.getId() + 1);
        return course;
    }

    @Override
    public Course getInvalidEntity() {
        return new Course();
    }
}
