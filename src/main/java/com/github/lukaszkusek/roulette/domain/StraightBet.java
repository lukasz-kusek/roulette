package com.github.lukaszkusek.roulette.domain;

import com.google.common.annotations.VisibleForTesting;

public class StraightBet extends AbstractBet {

    private static final int WIN_FACTOR = 36;

    private final int number;

    public StraightBet(int number, long amount) {
        super(amount);

        this.number = number;
    }

    @Override
    public BetResult calculateOutcome(int drawnBall) {
        if (number != drawnBall) {
            return BetResult.LOSE;
        }
        return new BetResult(Outcome.WIN, WIN_FACTOR * getAmount());
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
