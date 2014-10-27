package com.github.lukaszkusek.roulette.util;

import com.github.lukaszkusek.roulette.domain.Outcome;

public interface BetResultDefinition {

    void resultIs(Outcome outcome, long winnings);

    BetResultBuilderValues resultShouldBe(Outcome outcome, long winnings);
}
