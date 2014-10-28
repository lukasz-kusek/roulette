package com.github.lukaszkusek.roulette.domain.bets;

import org.junit.Test;

import static com.github.lukaszkusek.roulette.domain.bets.outcome.BetOutcome.lose;
import static com.github.lukaszkusek.roulette.domain.bets.outcome.BetOutcome.win;
import static com.github.lukaszkusek.roulette.util.BetResultBuilder.forBet;

public class StraightBetTest extends AbstractBetTest {

    @Test
    public void shouldCalculateOutcome() {
        shouldCalculateOutcome(
                forBet(straightBet(1, 1)).andDrawnBall(2).resultShouldBe(lose()),
                forBet(straightBet(36, 1)).andDrawnBall(3).resultShouldBe(lose()),
                forBet(straightBet(12, 10)).andDrawnBall(21).resultShouldBe(lose()),
                forBet(straightBet(14, 1)).andDrawnBall(23).resultShouldBe(lose()),

                forBet(straightBet(1, 1)).andDrawnBall(1).resultShouldBe(win(36)),
                forBet(straightBet(36, 1)).andDrawnBall(36).resultShouldBe(win(36)),
                forBet(straightBet(10, 1)).andDrawnBall(10).resultShouldBe(win(36)),
                forBet(straightBet(21, 1)).andDrawnBall(21).resultShouldBe(win(36)),

                forBet(straightBet(1, 2)).andDrawnBall(1).resultShouldBe(win(72)),
                forBet(straightBet(1, 3)).andDrawnBall(1).resultShouldBe(win(108)),
                forBet(straightBet(1, 4)).andDrawnBall(1).resultShouldBe(win(144))
        );
    }

    private Bet straightBet(int number, long amount) {
        return new StraightBet(number, amount);
    }

}
