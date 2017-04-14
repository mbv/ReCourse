package by.triumgroup.recourse.supplier.entity.model.impl;

import by.triumgroup.recourse.entity.model.Hometask;
import by.triumgroup.recourse.supplier.entity.model.EntityIntegerPKSupplier;

public class HometaskSupplier implements EntityIntegerPKSupplier<Hometask> {
    @Override
    public Hometask getValidEntityWithoutId() {
        Hometask hometask = new Hometask();
        hometask.setLessonId(getAnyId());
        hometask.setTask("task");
        return hometask;
    }

    @Override
    public Hometask getInvalidEntity() {
        return new Hometask();
    }
}
