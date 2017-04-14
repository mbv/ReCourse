package by.triumgroup.recourse.supplier.entity.model.impl;

import by.triumgroup.recourse.entity.model.StudentReport;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.supplier.entity.model.EntityIntegerPKSupplier;

public class StudentReportSupplier implements EntityIntegerPKSupplier<StudentReport> {
    private UserSupplier userSupplier = new UserSupplier();

    @Override
    public StudentReport getValidEntityWithoutId() {
        StudentReport studentReport = new StudentReport();
        studentReport.setCourseId(getAnyId());
        User teacher = userSupplier.getValidEntityWithId();
        User student = userSupplier.getValidEntityWithId();
        student.setId(teacher.getId() + 1);
        studentReport.setStudent(student);
        studentReport.setTeacher(teacher);
        studentReport.setHeading("heading");
        studentReport.setReport("report");
        return studentReport;
    }

    @Override
    public StudentReport getInvalidEntity() {
        return new StudentReport();
    }
}
