package com.github.lukaszkusek.roulette.domain.bets;

import com.google.common.annotations.VisibleForTesting;

public class StraightBet extends AbstractBet {

    private static final int WIN_FACTOR = 36;

    private final int number;

    public StraightBet(int number, long amount) {
        super(amount);

        this.number = number;
    }

    @Override
    protected boolean isWin(int drawnBall) {
        return number == drawnBall;
    }

    @Override
    protected int winFactor() {
        return WIN_FACTOR;
    }

    @VisibleForTesting
    protected int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
