package by.triumgroup.recourse.supplier.entity.dto.impl;

import by.triumgroup.recourse.entity.dto.RegistrationDetails;
import by.triumgroup.recourse.supplier.entity.dto.DtoSupplier;

public class RegistrationDetailsSupplier implements DtoSupplier<RegistrationDetails> {
    @Override
    public RegistrationDetails getValidDto() {
        RegistrationDetails result = new RegistrationDetails();
        result.setEmail("a@b.c");
        result.setName("Ivan");
        result.setSurname("Shimko");
        return result;
    }

    @Override
    public RegistrationDetails getInvalidDto() {
        return null;
    }
}
