package com.github.lukaszkusek.roulette.domain;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.internal.Objects;

public class StraightBetAssert extends AbstractAssert<StraightBetAssert, StraightBet> {

    private Objects objects = Objects.instance();

    private StraightBetAssert(StraightBet actual) {
        super(actual, StraightBetAssert.class);
    }

    public static StraightBetAssert assertThat(StraightBet actual) {
        return new StraightBetAssert(actual);
    }

    public StraightBetAssert hasNumber(int expected) {
        isNotNull();
        objects.assertEqual(info, actual.getNumber(), expected);
        return this;
    }
}
