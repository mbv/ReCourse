package by.triumgroup.recourse.supplier.entity.dto;

public interface DtoSupplier<T> {
    T getValidDto();
    T getInvalidDto();

}
