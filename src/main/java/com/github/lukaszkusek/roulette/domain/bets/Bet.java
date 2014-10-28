package com.github.lukaszkusek.roulette.domain.bets;

import com.github.lukaszkusek.roulette.domain.bets.outcome.BetOutcome;

public interface Bet {

    /**
     * Calculates outcome based on drawn ball and inner rules.
     */
    BetOutcome calculateOutcome(int drawnBall);
}
