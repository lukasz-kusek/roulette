package com.github.lukaszkusek.roulette.domain.bets;

import org.junit.Test;

import static com.github.lukaszkusek.roulette.domain.bets.outcome.BetOutcome.lose;
import static com.github.lukaszkusek.roulette.domain.bets.outcome.BetOutcome.win;
import static com.github.lukaszkusek.roulette.util.BetResultBuilder.forBet;

public class OddBetTest extends AbstractBetTest {

    @Test
    public void shouldCalculateOutcome() {
        shouldCalculateOutcome(
                forBet(oddBet(1)).andDrawnBall(1).resultShouldBe(win(2)),
                forBet(oddBet(1)).andDrawnBall(13).resultShouldBe(win(2)),
                forBet(oddBet(1)).andDrawnBall(35).resultShouldBe(win(2)),

                forBet(oddBet(1)).andDrawnBall(2).resultShouldBe(lose()),
                forBet(oddBet(1)).andDrawnBall(24).resultShouldBe(lose()),
                forBet(oddBet(1)).andDrawnBall(36).resultShouldBe(lose()),

                forBet(oddBet(4)).andDrawnBall(5).resultShouldBe(win(8)),
                forBet(oddBet(17)).andDrawnBall(9).resultShouldBe(win(34))
        );
    }

    private Bet oddBet(long amount) {
        return new OddBet(amount);
    }
}
