package com.github.lukaszkusek.roulette.domain.bets.outcome;

public interface BetOutcome {

    boolean isWin();

    long getWinnings();

    Outcome getOutcome();

    static BetOutcome win(long winnings) {
        return new Win(winnings);
    }

    static BetOutcome lose() {
        return Lose.INSTANCE;
    }
}
