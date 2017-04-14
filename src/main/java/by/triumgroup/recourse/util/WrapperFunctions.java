package by.triumgroup.recourse.util;

import java.util.Optional;

public class WrapperFunctions {

    @FunctionalInterface
    public interface WrapperFunction<R> {
        R call();
    }

    @FunctionalInterface
    public interface WrapperVoidFunction {
        void call();
    }

    @FunctionalInterface
    public interface WrapperOptionalFunction<R> {
        Optional<R> call();
    }


}
