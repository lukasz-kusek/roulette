package com.github.lukaszkusek.roulette.domain;

import org.junit.Test;

import static com.github.lukaszkusek.roulette.domain.Outcome.LOSE;
import static com.github.lukaszkusek.roulette.domain.Outcome.WIN;
import static com.github.lukaszkusek.roulette.util.BetResultBuilder.forBet;

public class OddBetTest extends AbstractBetTest {

    @Test
    public void shouldCalculateOutcome() {
        shouldCalculateOutcome(
                forBet(oddBet(1l)).andDrawnBall(1).resultShouldBe(WIN, 2l),
                forBet(oddBet(1l)).andDrawnBall(13).resultShouldBe(WIN, 2l),
                forBet(oddBet(1l)).andDrawnBall(35).resultShouldBe(WIN, 2l),

                forBet(oddBet(1l)).andDrawnBall(2).resultShouldBe(LOSE, 0l),
                forBet(oddBet(1l)).andDrawnBall(24).resultShouldBe(LOSE, 0l),
                forBet(oddBet(1l)).andDrawnBall(36).resultShouldBe(LOSE, 0l),

                forBet(oddBet(4l)).andDrawnBall(5).resultShouldBe(WIN, 8l),
                forBet(oddBet(17l)).andDrawnBall(9).resultShouldBe(WIN, 34l)
        );
    }

    private Bet oddBet(long amount) {
        return new OddBet(amount);
    }
}
