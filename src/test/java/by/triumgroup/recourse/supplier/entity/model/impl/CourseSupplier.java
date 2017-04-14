package by.triumgroup.recourse.supplier.entity.model.impl;

import by.triumgroup.recourse.entity.model.Course;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.supplier.entity.model.EntityIntegerPKSupplier;

public class CourseSupplier implements EntityIntegerPKSupplier<Course> {
    private UserSupplier userSupplier = new UserSupplier();

    @Override
    public Course getValidEntityWithoutId() {
        Course course =  new Course();
        course.setTitle("Title");
        course.setOrganizer(userSupplier.getValidEntityWithId());
        course.setDescription("Description");
        course.setMaxStudents(10);
        course.setStatus(Course.Status.ONGOING);
        User teacher = userSupplier.getValidEntityWithId();
        teacher.setId(teacher.getId() + 1);
        course.setTeacher(teacher);
        return course;
    }

    @Override
    public Course getInvalidEntity() {
        return new Course();
    }
}
