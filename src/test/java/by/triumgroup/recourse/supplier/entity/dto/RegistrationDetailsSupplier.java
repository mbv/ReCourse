package by.triumgroup.recourse.supplier.entity.dto;

import by.triumgroup.recourse.entity.dto.RegistrationDetails;

import java.util.function.Supplier;

public class RegistrationDetailsSupplier implements Supplier<RegistrationDetails> {
    public RegistrationDetails get() {
        RegistrationDetails result = new RegistrationDetails();
        result.setEmail("a@b.c");
        result.setName("Ivan");
        result.setSurname("Shimko");
        result.setPassword("password");
        result.setPasswordConfirmation("password");
        return result;
    }
}
