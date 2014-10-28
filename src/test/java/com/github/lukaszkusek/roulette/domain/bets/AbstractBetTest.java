package com.github.lukaszkusek.roulette.domain.bets;

import com.google.common.collect.ImmutableList;
import com.github.lukaszkusek.roulette.domain.bets.outcome.BetOutcome;
import com.github.lukaszkusek.roulette.util.BetResultBuilderValues;

import static com.github.lukaszkusek.roulette.util.Assertions.assertThat;

public abstract class AbstractBetTest {

    protected void shouldCalculateOutcome(BetResultBuilderValues... values) {
        ImmutableList.copyOf(values).forEach(
                input -> {
                    // given
                    Bet bet = input.getBet();

                    // when
                    BetOutcome betOutcome = bet.calculateOutcome(input.getDrawnBall());

                    // then
                    assertThat(betOutcome)
                            .hasOutcome(input.getOutcome())
                            .hasWinnings(input.getWinnings());
                }
        );
    }
}
