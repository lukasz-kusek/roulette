package com.github.lukaszkusek.roulette.domain;

public class OddBet extends AbstractBet {

    private static final int WIN_FACTOR = 2;

    public OddBet(long amount) {
        super(amount);
    }

    @Override
    public BetResult calculateOutcome(int drawnBall) {
        if (drawnBall % 2 != 1) {
            return BetResult.LOSE;
        }
        return new BetResult(Outcome.WIN, WIN_FACTOR * getAmount());
    }

    @Override
    public String toString() {
        return "ODD";
    }
}
