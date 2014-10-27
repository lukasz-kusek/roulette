package com.github.lukaszkusek.roulette.domain;

public class BetResult {

    public static final BetResult LOSE = new BetResult(Outcome.LOSE, 0l);

    private final Outcome outcome;
    private final long winnings;

    public BetResult(Outcome outcome, long winnings) {
        this.outcome = outcome;
        this.winnings = winnings;
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public long getWinnings() {
        return winnings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BetResult betResult = (BetResult) o;

        if (winnings != betResult.winnings) return false;
        if (outcome != betResult.outcome) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = outcome.hashCode();
        result = 31 * result + (int) (winnings ^ (winnings >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "BetResult [" +
                "outcome=" + outcome +
                ", winnings=" + winnings +
                ']';
    }
}
