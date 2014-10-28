package com.github.lukaszkusek.roulette.domain.bets;

import com.google.common.base.Preconditions;

public class BetFactory {

    public Bet from(String bet, long amount) {
        switch (bet) {
            case "ODD":
                return new OddBet(amount);
            case "EVEN":
                return new EvenBet(amount);
            default:
                return straightBet(bet, amount);
        }
    }

    private Bet straightBet(String bet, long amount) {
        try {
            Integer number = Integer.valueOf(bet);

            validateBet(number);

            return new StraightBet(number, amount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Bet " + bet + " is not supported.");
        }
    }

    private void validateBet(Integer number) {
        Preconditions.checkArgument(number >= 1 && number <= 36, "Bet " + number + " is not supported.");
    }
}
