package com.github.lukaszkusek.roulette.util;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ThrowableCaptor {

    public static <T> Throwable captureThrowable(Consumer<T> consumer, T value) {
        try {
            consumer.accept(value);
            return null;
        } catch (Throwable caught) {
            return caught;
        }
    }

    public static <T, U> Throwable captureThrowable(BiConsumer<T, U> consumer, T value1, U value2) {
        try {
            consumer.accept(value1, value2);
            return null;
        } catch (Throwable caught) {
            return caught;
        }
    }
}
