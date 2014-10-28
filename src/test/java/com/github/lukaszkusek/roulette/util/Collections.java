package com.github.lukaszkusek.roulette.util;

import com.google.common.collect.ImmutableList;

import java.util.List;

public class Collections {

    public static <T> List<T> list(T... a) {
        return ImmutableList.copyOf(a);
    }
}
