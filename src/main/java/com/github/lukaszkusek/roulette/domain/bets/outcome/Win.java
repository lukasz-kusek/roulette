package com.github.lukaszkusek.roulette.domain.bets.outcome;

public class Win implements BetOutcome {

    private final long winnings;

    public Win(long winnings) {
        this.winnings = winnings;
    }

    @Override
    public boolean isWin() {
        return true;
    }

    @Override
    public long getWinnings() {
        return winnings;
    }

    @Override
    public Outcome getOutcome() {
        return Outcome.WIN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Win win = (Win) o;

        return winnings == win.winnings;

    }

    @Override
    public int hashCode() {
        return (int) (winnings ^ (winnings >>> 32));
    }

    @Override
    public String toString() {
        return "Win [" +
                "winnings=" + winnings +
                ']';
    }
}
