package com.github.lukaszkusek.roulette.domain.bets;

import com.github.lukaszkusek.roulette.domain.bets.outcome.BetOutcome;
import com.github.lukaszkusek.roulette.domain.bets.outcome.Win;

public abstract class AbstractBet implements Bet {

    private final long amount;

    protected AbstractBet(long amount) {
        this.amount = amount;
    }

    @Override
    public BetOutcome calculateOutcome(int drawnBall) {
        if (!isWin(drawnBall)) {
            return BetOutcome.lose();
        }
        return new Win(winFactor() * getAmount());
    }

    protected abstract boolean isWin(int drawnBall);
    protected abstract int winFactor();

    protected long getAmount() {
        return amount;
    }
}
