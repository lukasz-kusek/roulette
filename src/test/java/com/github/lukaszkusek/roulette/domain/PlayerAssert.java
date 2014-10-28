package com.github.lukaszkusek.roulette.domain;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.internal.Objects;

public class PlayerAssert extends AbstractAssert<PlayerAssert, Player> {

    private Objects objects = Objects.instance();

    public PlayerAssert(Player actual) {
        super(actual, PlayerAssert.class);
    }

    public PlayerAssert hasName(String expected) {
        isNotNull();
        objects.assertEqual(info, actual.getName(), expected);
        return this;
    }

    public PlayerAssert hasTotalWin(long expected) {
        isNotNull();
        objects.assertEqual(info, actual.getTotalWin(), expected);
        return this;
    }

    public PlayerAssert hasTotalBet(long expected) {
        isNotNull();
        objects.assertEqual(info, actual.getTotalBet(), expected);
        return this;
    }
}
