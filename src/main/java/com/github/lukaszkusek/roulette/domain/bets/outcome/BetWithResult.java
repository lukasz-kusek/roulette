package com.github.lukaszkusek.roulette.domain.bets.outcome;

import com.github.lukaszkusek.roulette.domain.bets.Bet;

public class BetWithResult {

    private final Bet bet;
    private final BetOutcome betOutcome;

    public BetWithResult(Bet bet, BetOutcome betOutcome) {
        this.bet = bet;
        this.betOutcome = betOutcome;
    }

    public Bet getBet() {
        return bet;
    }

    public boolean isWin() {
        return betOutcome.isWin();
    }

    public Outcome getOutcome() {
        return betOutcome.getOutcome();
    }

    public long getWinnings() {
        return betOutcome.getWinnings();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BetWithResult that = (BetWithResult) o;

        if (!bet.equals(that.bet)) return false;
        if (!betOutcome.equals(that.betOutcome)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bet.hashCode();
        result = 31 * result + betOutcome.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BetWithResult [" +
                "bet=" + bet +
                ", betOutcome=" + betOutcome +
                ']';
    }
}
