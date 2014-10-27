package com.github.lukaszkusek.roulette.domain;

import com.google.common.collect.ImmutableList;
import com.github.lukaszkusek.roulette.util.BetResultBuilderValues;

import static com.github.lukaszkusek.roulette.domain.BetResultAssert.assertThat;

public abstract class AbstractBetTest {

    protected void shouldCalculateOutcome(BetResultBuilderValues... values) {
        ImmutableList.copyOf(values).forEach(
                input -> {
                    // given
                    Bet bet = input.getBet();

                    // when
                    BetResult betResult = bet.calculateOutcome(input.getDrawnBall());

                    // then
                    assertThat(betResult)
                            .hasOutcome(input.getOutcome())
                            .hasWinnings(input.getWinnings());
                }
        );
    }
}
