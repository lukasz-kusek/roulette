package com.github.lukaszkusek.roulette.domain.bets.outcome;

public enum Lose implements BetOutcome {
    INSTANCE;

    @Override
    public boolean isWin() {
        return false;
    }

    @Override
    public long getWinnings() {
        return 0;
    }

    @Override
    public Outcome getOutcome() {
        return Outcome.LOSE;
    }
}
