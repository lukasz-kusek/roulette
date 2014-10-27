package com.github.lukaszkusek.roulette.util;

import com.github.lukaszkusek.roulette.domain.Bet;
import com.github.lukaszkusek.roulette.domain.BetResult;
import com.github.lukaszkusek.roulette.domain.Outcome;

import static org.mockito.BDDMockito.given;

public class BetResultBuilder implements BetResultDefinition, BetDefinition, BetResultBuilderValues {

    private Bet bet;
    private int drawnBall;
    private Outcome outcome;
    private long winnings;

    private BetResultBuilder(Bet bet) {
        this.bet = bet;
    }

    public static BetDefinition forBet(Bet bet) {
        return new BetResultBuilder(bet);
    }

    @Override
    public BetResultDefinition andDrawnBall(int drawnBall) {
        this.drawnBall = drawnBall;
        return this;
    }


    @Override
    public void resultIs(Outcome outcome, long winnings) {
        given(bet.calculateOutcome(drawnBall)).willReturn(new BetResult(outcome, winnings));
    }

    @Override
    public BetResultBuilderValues resultShouldBe(Outcome outcome, long winnings) {
        this.outcome = outcome;
        this.winnings = winnings;
        return this;
    }

    @Override
    public Bet getBet() {
        return bet;
    }

    @Override
    public int getDrawnBall() {
        return drawnBall;
    }

    @Override
    public Outcome getOutcome() {
        return outcome;
    }

    @Override
    public long getWinnings() {
        return winnings;
    }
}
