package com.github.lukaszkusek.roulette.util;

import com.github.lukaszkusek.roulette.domain.Bet;
import com.github.lukaszkusek.roulette.domain.Outcome;

public interface BetResultBuilderValues {

    Bet getBet();

    int getDrawnBall();

    Outcome getOutcome();

    long getWinnings();
}
