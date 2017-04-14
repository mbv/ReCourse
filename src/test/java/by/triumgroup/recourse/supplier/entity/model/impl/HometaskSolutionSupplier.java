package by.triumgroup.recourse.supplier.entity.model.impl;

import by.triumgroup.recourse.entity.model.HometaskSolution;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.supplier.entity.model.EntityIntegerPKSupplier;

public class HometaskSolutionSupplier implements EntityIntegerPKSupplier<HometaskSolution> {
    private UserSupplier userSupplier = new UserSupplier();

    @Override
    public HometaskSolution getValidEntityWithoutId() {
        HometaskSolution hometaskSolution = new HometaskSolution();
        hometaskSolution.setHometaskId(getAnyId());
        User student = userSupplier.getValidEntityWithId();
        hometaskSolution.setStudent(student);
        hometaskSolution.setSolution("solution");
        return hometaskSolution;
    }

    @Override
    public HometaskSolution getInvalidEntity() {
        return new HometaskSolution();
    }
}
