package com.github.lukaszkusek.roulette.domain.bets;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.internal.Objects;

public class StraightBetAssert extends AbstractAssert<StraightBetAssert, StraightBet> {

    private Objects objects = Objects.instance();

    public StraightBetAssert(StraightBet actual) {
        super(actual, StraightBetAssert.class);
    }

    public StraightBetAssert hasNumber(int expected) {
        isNotNull();
        objects.assertEqual(info, actual.getNumber(), expected);
        return this;
    }
}
