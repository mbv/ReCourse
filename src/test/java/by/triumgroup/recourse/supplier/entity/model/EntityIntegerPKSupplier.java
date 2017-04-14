package by.triumgroup.recourse.supplier.entity.model;

import by.triumgroup.recourse.entity.model.BaseEntity;
import org.springframework.data.util.Pair;

public interface EntityIntegerPKSupplier<E extends BaseEntity<Integer>> extends EntitySupplier<E, Integer>{
    @Override
    default Integer getAnyId(){
        return 1;
    }

    @Override
    default Pair<Integer, Integer> getDifferentIds(){
        return Pair.of(1, 2);
    }
}
