package com.github.lukaszkusek.roulette.util;

import java.util.stream.IntStream;

public class IntStreams {

    public static IntStream executeTimes(int times) {
        return IntStream.range(1, times + 1);
    }
}
