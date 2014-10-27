package com.github.lukaszkusek.roulette.util;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class Maps {

    public static <K, V> Map<K, V> map(K k1, V v1) {
        return ImmutableMap.of(k1, v1);
    }

    public static <K, V> Map<K, V> map(K k1, V v1, K k2, V v2) {
        return ImmutableMap.of(k1, v1, k2, v2);
    }
}
