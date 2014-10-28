package com.github.lukaszkusek.roulette.util;

import com.github.lukaszkusek.roulette.domain.bets.outcome.BetOutcome;

public interface BetResultDefinition {

    void resultIs(BetOutcome betOutcome);

    BetResultBuilderValues resultShouldBe(BetOutcome betOutcome);
}
