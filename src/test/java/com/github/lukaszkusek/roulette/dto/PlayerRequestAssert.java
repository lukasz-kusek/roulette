package com.github.lukaszkusek.roulette.dto;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.internal.Objects;

public class PlayerRequestAssert extends AbstractAssert<PlayerRequestAssert, PlayerRequest> {

    private Objects objects = Objects.instance();

    private PlayerRequestAssert(PlayerRequest actual) {
        super(actual, PlayerRequestAssert.class);
    }

    public static PlayerRequestAssert assertThat(PlayerRequest actual) {
        return new PlayerRequestAssert(actual);
    }

    public PlayerRequestAssert hasBet(String expected) {
        isNotNull();
        objects.assertEqual(info, actual.getBet(), expected);
        return this;
    }

    public PlayerRequestAssert hasAmount(long expected) {
        isNotNull();
        objects.assertEqual(info, actual.getAmount(), expected);
        return this;
    }

    public PlayerRequestAssert hasPlayerName(String expected) {
        isNotNull();
        objects.assertEqual(info, actual.getPlayerName(), expected);
        return this;
    }
}
