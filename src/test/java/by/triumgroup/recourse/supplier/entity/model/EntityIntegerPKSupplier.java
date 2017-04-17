package by.triumgroup.recourse.supplier.entity.model;

import by.triumgroup.recourse.entity.model.BaseEntity;
import org.springframework.data.util.Pair;

import java.util.Random;

public interface EntityIntegerPKSupplier<E extends BaseEntity<Integer>> extends EntitySupplier<E, Integer>{
    @Override
    default Integer getAnyId(){
        return new Random().ints(1, Integer.MAX_VALUE).findFirst().getAsInt();
    }

    @Override
    default Pair<Integer, Integer> getDifferentIds(){
        Integer first = getAnyId();
        Integer second = first + 1;
        return Pair.of(first, second);
    }
}
