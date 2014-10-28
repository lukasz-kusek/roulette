package com.github.lukaszkusek.roulette.util;

import com.github.lukaszkusek.roulette.domain.bets.outcome.BetOutcome;
import com.github.lukaszkusek.roulette.domain.bets.outcome.BetOutcomeAssert;
import com.github.lukaszkusek.roulette.domain.Player;
import com.github.lukaszkusek.roulette.domain.PlayerAssert;
import com.github.lukaszkusek.roulette.domain.Round;
import com.github.lukaszkusek.roulette.domain.RoundAssert;
import com.github.lukaszkusek.roulette.domain.bets.AbstractBet;
import com.github.lukaszkusek.roulette.domain.bets.AbstractBetAssert;
import com.github.lukaszkusek.roulette.domain.bets.Bet;
import com.github.lukaszkusek.roulette.domain.bets.StraightBet;
import com.github.lukaszkusek.roulette.domain.bets.StraightBetAssert;
import com.github.lukaszkusek.roulette.dto.PlayerRequest;
import com.github.lukaszkusek.roulette.dto.PlayerRequestAssert;

public class Assertions extends org.assertj.core.api.Assertions {

    public static AbstractBetAssert assertThat(AbstractBet actual) {
        return new AbstractBetAssert(actual);
    }

    public static AbstractBetAssert assertThat(Bet actual) {
        return new AbstractBetAssert((AbstractBet) actual);
    }

    public static StraightBetAssert assertThat(StraightBet actual) {
        return new StraightBetAssert(actual);
    }

    public static BetOutcomeAssert assertThat(BetOutcome actual) {
        return new BetOutcomeAssert(actual);
    }

    public static PlayerAssert assertThat(Player actual) {
        return new PlayerAssert(actual);
    }

    public static RoundAssert assertThat(Round actual) {
        return new RoundAssert(actual);
    }

    public static PlayerRequestAssert assertThat(PlayerRequest actual) {
        return new PlayerRequestAssert(actual);
    }
}
