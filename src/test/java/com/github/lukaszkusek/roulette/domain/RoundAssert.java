package com.github.lukaszkusek.roulette.domain;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.data.MapEntry;
import org.assertj.core.internal.Objects;
import org.assertj.guava.api.Assertions;

public class RoundAssert extends AbstractAssert<RoundAssert, Round> {

    private Objects objects = Objects.instance();

    private RoundAssert(Round actual) {
        super(actual, RoundAssert.class);
    }

    public static RoundAssert assertThat(Round actual) {
        return new RoundAssert(actual);
    }

    public RoundAssert hasDrawnBall(int expected) {
        isNotNull();
        objects.assertEqual(info, actual.getDrawnBall().get(), expected);
        return this;
    }

    public RoundAssert hasPlayersBets(MapEntry... entries) {
        isNotNull();
        Assertions.assertThat(actual.getPlayersBets()).hasSize(entries.length).contains(entries);
        return this;
    }

    public RoundAssert hasPlayersBetsWithResults(MapEntry... entries) {
        isNotNull();
        org.assertj.core.api.Assertions.assertThat(actual.getPlayersBetsWithResults())
                                       .hasSize(entries.length)
                                       .contains(entries);
        return this;
    }

}
