package by.triumgroup.recourse.supplier.entity.model;

import by.triumgroup.recourse.entity.model.BaseEntity;
import org.springframework.data.util.Pair;

public interface EntitySupplier<E extends BaseEntity<ID>, ID> {
    E getValidEntityWithoutId();

    default E getValidEntityWithId(){
        E result = getValidEntityWithoutId();
        result.setId(getAnyId());
        return result;
    }

    E getInvalidEntity();

    ID getAnyId();

    Pair<ID, ID> getDifferentIds();
}
