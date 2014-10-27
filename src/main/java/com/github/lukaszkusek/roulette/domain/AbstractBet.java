package com.github.lukaszkusek.roulette.domain;

public abstract class AbstractBet implements Bet {

    private final long amount;

    protected AbstractBet(long amount) {
        this.amount = amount;
    }

    protected long getAmount() {
        return amount;
    }
}
