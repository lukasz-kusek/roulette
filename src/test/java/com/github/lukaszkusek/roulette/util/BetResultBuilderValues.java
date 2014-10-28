package com.github.lukaszkusek.roulette.util;

import com.github.lukaszkusek.roulette.domain.bets.Bet;
import com.github.lukaszkusek.roulette.domain.bets.outcome.Outcome;

public interface BetResultBuilderValues {

    Bet getBet();

    int getDrawnBall();

    Outcome getOutcome();

    long getWinnings();
}
