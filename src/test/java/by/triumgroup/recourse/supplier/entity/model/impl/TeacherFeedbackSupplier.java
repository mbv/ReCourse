package by.triumgroup.recourse.supplier.entity.model.impl;

import by.triumgroup.recourse.entity.model.TeacherFeedback;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.supplier.entity.model.EntityIntegerPKSupplier;

public class TeacherFeedbackSupplier implements EntityIntegerPKSupplier<TeacherFeedback> {
    private UserSupplier userSupplier = new UserSupplier();

    @Override
    public TeacherFeedback getValidEntityWithoutId() {
        TeacherFeedback teacherFeedback = new TeacherFeedback();
        teacherFeedback.setHeading("heading");
        teacherFeedback.setReport("report");
        User teacher = userSupplier.getValidEntityWithId();
        User student = userSupplier.getValidEntityWithId();
        student.setId(teacher.getId() + 1);
        teacherFeedback.setStudent(student);
        teacherFeedback.setTeacher(teacher);
        return teacherFeedback;
    }

    @Override
    public TeacherFeedback getInvalidEntity() {
        return new TeacherFeedback();
    }
}
