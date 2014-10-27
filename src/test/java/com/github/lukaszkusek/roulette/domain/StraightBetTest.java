package com.github.lukaszkusek.roulette.domain;

import org.junit.Test;

import static com.github.lukaszkusek.roulette.util.BetResultBuilder.forBet;

public class StraightBetTest extends AbstractBetTest {

    @Test
    public void shouldCalculateOutcome() {
        shouldCalculateOutcome(
                forBet(straightBet(1, 1l)).andDrawnBall(2).resultShouldBe(Outcome.LOSE, 0l),
                forBet(straightBet(36, 1l)).andDrawnBall(3).resultShouldBe(Outcome.LOSE, 0l),
                forBet(straightBet(12, 10l)).andDrawnBall(21).resultShouldBe(Outcome.LOSE, 0l),
                forBet(straightBet(14, 1l)).andDrawnBall(23).resultShouldBe(Outcome.LOSE, 0l),

                forBet(straightBet(1, 1l)).andDrawnBall(1).resultShouldBe(Outcome.WIN, 36l),
                forBet(straightBet(36, 1l)).andDrawnBall(36).resultShouldBe(Outcome.WIN, 36l),
                forBet(straightBet(10, 1l)).andDrawnBall(10).resultShouldBe(Outcome.WIN, 36l),
                forBet(straightBet(21, 1l)).andDrawnBall(21).resultShouldBe(Outcome.WIN, 36l),

                forBet(straightBet(1, 2l)).andDrawnBall(1).resultShouldBe(Outcome.WIN, 72l),
                forBet(straightBet(1, 3l)).andDrawnBall(1).resultShouldBe(Outcome.WIN, 108l),
                forBet(straightBet(1, 4l)).andDrawnBall(1).resultShouldBe(Outcome.WIN, 144l)
        );
    }

    private Bet straightBet(int number, long amount) {
        return new StraightBet(number, amount);
    }

}
