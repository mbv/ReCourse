package by.triumgroup.recourse.supplier.entity.model.impl;

import by.triumgroup.recourse.entity.model.CourseFeedback;
import by.triumgroup.recourse.supplier.entity.model.EntityIntegerPKSupplier;

public class CourseFeedbackSupplier implements EntityIntegerPKSupplier<CourseFeedback> {
    private UserSupplier userSupplier = new UserSupplier();

    @Override
    public CourseFeedback getValidEntityWithoutId() {
        CourseFeedback courseFeedback = new CourseFeedback();
        courseFeedback.setCons("cons");
        courseFeedback.setHeading("heading");
        courseFeedback.setPros("pros");
        courseFeedback.setReport("report");
        courseFeedback.setCourseId(getAnyId());
        courseFeedback.setStudent(userSupplier.getValidEntityWithId());
        return courseFeedback;
    }

    @Override
    public CourseFeedback getInvalidEntity() {
        return new CourseFeedback();
    }
}
