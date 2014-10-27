package com.github.lukaszkusek.roulette.domain;

public interface Bet {

    /**
     * Calculates outcome based on drawn ball and inner rules.
     */
    BetResult calculateOutcome(int drawnBall);
}
