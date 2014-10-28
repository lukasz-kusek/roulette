package com.github.lukaszkusek.roulette.domain;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.github.lukaszkusek.roulette.util.AmountConverter;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class Player {

    private final String name;
    private final AtomicLong totalWin;
    private final AtomicLong totalBet;

    public Player(String line) {
        Iterable<String> split = Splitter.on(",").trimResults().split(line);

        int size = Iterables.size(split);
        Preconditions.checkArgument(size == 1 || size == 3);

        name = Iterables.get(split, 0);
        totalWin = getAmount(split, 1);
        totalBet = getAmount(split, 2);
    }

    private AtomicLong getAmount(Iterable<String> split, int position) {
        return Optional.ofNullable(Iterables.get(split, position, null))
                       .map(AmountConverter::convert)
                       .map(AtomicLong::new)
                       .orElse(new AtomicLong());
    }

    public String getName() {
        return name;
    }

    public Long getTotalWin() {
        return totalWin.get();
    }

    public void addToTotalWin(long amount) {
        totalWin.addAndGet(amount);
    }

    public Long getTotalBet() {
        return totalBet.get();
    }

    public void addToTotalBet(long amount) {
        totalBet.addAndGet(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return name.equals(player.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Player[ " +
                "name='" + name + '\'' +
                ", totalWin=" + totalWin +
                ", totalBet=" + totalBet +
                " ]";
    }
}
