package com.github.lukaszkusek.roulette.dto;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.github.lukaszkusek.roulette.util.AmountConverter;

public class ConsolePlayerRequest implements PlayerRequest {

    private final String playerName;
    private final String type;
    private final long amount;

    public ConsolePlayerRequest(String line) {
        Iterable<String> split = Splitter.on(" ").trimResults().omitEmptyStrings().split(line);
        Preconditions.checkArgument(Iterables.size(split) == 3, "Input line format: <NAME> <BET> <AMOUNT>");

        this.playerName = Iterables.get(split, 0);
        this.type = Iterables.get(split, 1);
        this.amount = AmountConverter.convert(Iterables.get(split, 2));
    }

    @Override
    public String getPlayerName() {
        return playerName;
    }

    @Override
    public String getBet() {
        return type;
    }

    @Override
    public long getAmount() {
        return amount;
    }
}
