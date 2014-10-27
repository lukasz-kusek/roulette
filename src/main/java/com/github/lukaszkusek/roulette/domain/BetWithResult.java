package com.github.lukaszkusek.roulette.domain;

public class BetWithResult {

    private final Bet bet;
    private final BetResult betResult;

    public BetWithResult(Bet bet, BetResult betResult) {
        this.bet = bet;
        this.betResult = betResult;
    }

    public Bet getBet() {
        return bet;
    }

    public Outcome getOutcome() {
        return betResult.getOutcome();
    }

    public long getWinnings() {
        return betResult.getWinnings();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BetWithResult that = (BetWithResult) o;

        if (!bet.equals(that.bet)) return false;
        if (!betResult.equals(that.betResult)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bet.hashCode();
        result = 31 * result + betResult.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BetWithResult [" +
                "bet=" + bet +
                ", betResult=" + betResult +
                ']';
    }
}
