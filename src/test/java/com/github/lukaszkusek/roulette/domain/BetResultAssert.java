package com.github.lukaszkusek.roulette.domain;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.internal.Objects;

public class BetResultAssert extends AbstractAssert<BetResultAssert, BetResult> {

    private Objects objects = Objects.instance();

    private BetResultAssert(BetResult actual) {
        super(actual, BetResultAssert.class);
    }

    public static BetResultAssert assertThat(BetResult actual) {
        return new BetResultAssert(actual);
    }

    public BetResultAssert hasOutcome(Outcome expected) {
        isNotNull();
        objects.assertEqual(info, actual.getOutcome(), expected);
        return this;
    }

    public BetResultAssert hasWinnings(long expected) {
        isNotNull();
        objects.assertEqual(info, actual.getWinnings(), expected);
        return this;
    }
}
