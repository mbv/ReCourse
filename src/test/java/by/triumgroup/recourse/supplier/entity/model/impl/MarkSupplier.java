package by.triumgroup.recourse.supplier.entity.model.impl;

import by.triumgroup.recourse.entity.model.Mark;
import by.triumgroup.recourse.supplier.entity.model.EntityIntegerPKSupplier;

public class MarkSupplier implements EntityIntegerPKSupplier<Mark> {
    @Override
    public Mark getValidEntityWithoutId() {
        Mark mark = new Mark();
        mark.setSolutionId(getAnyId());
        mark.setScore(10);
        mark.setComment("comment");
        return mark;
    }

    @Override
    public Mark getInvalidEntity() {
        return new Mark();
    }
}
