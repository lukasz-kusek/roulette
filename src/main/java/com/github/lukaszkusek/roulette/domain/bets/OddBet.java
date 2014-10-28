package com.github.lukaszkusek.roulette.domain.bets;

public class OddBet extends AbstractBet {

    private static final int WIN_FACTOR = 2;

    public OddBet(long amount) {
        super(amount);
    }

    @Override
    protected boolean isWin(int drawnBall) {
        return drawnBall % 2 == 1;
    }

    @Override
    protected int winFactor() {
        return WIN_FACTOR;
    }

    @Override
    public String toString() {
        return "ODD";
    }
}
