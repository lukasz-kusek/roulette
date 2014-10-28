package com.github.lukaszkusek.roulette.domain.bets.outcome;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.internal.Objects;

public class BetOutcomeAssert extends AbstractAssert<BetOutcomeAssert, BetOutcome> {

    private Objects objects = Objects.instance();

    public BetOutcomeAssert(BetOutcome actual) {
        super(actual, BetOutcomeAssert.class);
    }

    public BetOutcomeAssert hasOutcome(Outcome expected) {
        isNotNull();
        objects.assertEqual(info, actual.getOutcome(), expected);
        return this;
    }

    public BetOutcomeAssert hasWinnings(long expected) {
        isNotNull();
        objects.assertEqual(info, actual.getWinnings(), expected);
        return this;
    }
}
