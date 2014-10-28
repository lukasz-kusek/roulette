package com.github.lukaszkusek.roulette.domain.bets;

import org.junit.Test;

import static com.github.lukaszkusek.roulette.domain.bets.outcome.BetOutcome.lose;
import static com.github.lukaszkusek.roulette.domain.bets.outcome.BetOutcome.win;
import static com.github.lukaszkusek.roulette.util.BetResultBuilder.forBet;

public class EvenBetTest extends AbstractBetTest {

    @Test
    public void shouldCalculateOutcome() {
        shouldCalculateOutcome(
                forBet(evenBet(1)).andDrawnBall(1).resultShouldBe(lose()),
                forBet(evenBet(1)).andDrawnBall(13).resultShouldBe(lose()),
                forBet(evenBet(1)).andDrawnBall(35).resultShouldBe(lose()),

                forBet(evenBet(1)).andDrawnBall(2).resultShouldBe(win(2)),
                forBet(evenBet(1)).andDrawnBall(24).resultShouldBe(win(2)),
                forBet(evenBet(1)).andDrawnBall(36).resultShouldBe(win(2)),

                forBet(evenBet(2)).andDrawnBall(4).resultShouldBe(win(4)),
                forBet(evenBet(35)).andDrawnBall(8).resultShouldBe(win(70))
        );
    }

    private Bet evenBet(long amount) {
        return new EvenBet(amount);
    }

}
