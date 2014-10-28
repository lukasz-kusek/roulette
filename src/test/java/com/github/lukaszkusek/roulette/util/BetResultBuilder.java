package com.github.lukaszkusek.roulette.util;

import com.github.lukaszkusek.roulette.domain.bets.Bet;
import com.github.lukaszkusek.roulette.domain.bets.outcome.BetOutcome;
import com.github.lukaszkusek.roulette.domain.bets.outcome.Outcome;

import static org.mockito.BDDMockito.given;

public class BetResultBuilder implements BetResultDefinition, BetDefinition, BetResultBuilderValues {

    private Bet bet;
    private int drawnBall;
    private BetOutcome betOutcome;

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
    public void resultIs(BetOutcome betOutcome) {
        given(bet.calculateOutcome(drawnBall)).willReturn(betOutcome);
    }

    @Override
    public BetResultBuilderValues resultShouldBe(BetOutcome betOutcome) {
        this.betOutcome = betOutcome;
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
        return betOutcome.getOutcome();
    }

    @Override
    public long getWinnings() {
        return betOutcome.getWinnings();
    }
}
