package com.github.lukaszkusek.roulette.domain;

import org.junit.Test;

import static com.github.lukaszkusek.roulette.domain.Outcome.LOSE;
import static com.github.lukaszkusek.roulette.domain.Outcome.WIN;
import static com.github.lukaszkusek.roulette.util.BetResultBuilder.forBet;

public class EvenBetTest extends AbstractBetTest {

    @Test
    public void shouldCalculateOutcome() {
        shouldCalculateOutcome(
                forBet(evenBet(1l)).andDrawnBall(1).resultShouldBe(LOSE, 0l),
                forBet(evenBet(1l)).andDrawnBall(13).resultShouldBe(LOSE, 0l),
                forBet(evenBet(1l)).andDrawnBall(35).resultShouldBe(LOSE, 0l),

                forBet(evenBet(1l)).andDrawnBall(2).resultShouldBe(WIN, 2l),
                forBet(evenBet(1l)).andDrawnBall(24).resultShouldBe(WIN, 2l),
                forBet(evenBet(1l)).andDrawnBall(36).resultShouldBe(WIN, 2l),

                forBet(evenBet(2l)).andDrawnBall(4).resultShouldBe(WIN, 4l),
                forBet(evenBet(35l)).andDrawnBall(8).resultShouldBe(WIN, 70l)
        );
    }

    private Bet evenBet(long amount) {
        return new EvenBet(amount);
    }

}
