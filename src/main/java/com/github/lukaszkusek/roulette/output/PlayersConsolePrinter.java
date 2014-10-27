package com.github.lukaszkusek.roulette.output;

import com.google.common.base.Strings;
import com.github.lukaszkusek.roulette.domain.Player;
import com.github.lukaszkusek.roulette.repository.Players;
import com.github.lukaszkusek.roulette.util.AmountConverter;

import java.util.Comparator;
import java.util.function.Function;

class PlayersConsolePrinter {

    private static final int MINIMAL_NAME_COLUMN_SIZE = 7;
    private static final int MINIMAL_TOTAL_WIN_COLUMN_SIZE = 10;
    private static final int MINIMAL_TOTAL_BET_COLUMN_SIZE = 10;

    private final Players players;
    private final int nameColumnSize;
    private final int totalWinColumnSize;
    private final int totalBetColumnSize;

    PlayersConsolePrinter(Players players) {
        this.players = players;
        nameColumnSize = getSize(players, Player::getName, MINIMAL_NAME_COLUMN_SIZE);
        totalWinColumnSize = getSize(players, Player::getTotalWin, MINIMAL_TOTAL_WIN_COLUMN_SIZE);
        totalBetColumnSize = getSize(players, Player::getTotalBet, MINIMAL_TOTAL_BET_COLUMN_SIZE);
    }

    private Integer getSize(Players players, Function<Player, ?> function, int minimalColumnSize) {
        return players.getAll()
                      .stream()
                      .map(function)
                      .map(String::valueOf)
                      .map(String::length)
                      .max(Comparator.<Integer>naturalOrder())
                      .map(size -> size < minimalColumnSize ? minimalColumnSize : size + 1)
                      .orElse(minimalColumnSize);
    }

    void printTotals() {
        printResultsLine("Player", "Total Win", "Total Bet");

        players.getAll().forEach(
                player ->
                        printResultsLine(
                                player.getName(),
                                AmountConverter.convert(player.getTotalWin()),
                                AmountConverter.convert(player.getTotalBet())));

        System.out.println();
    }

    private void printResultsLine(String name, String totalWin, String totalBet) {
        System.out.println(
                Strings.padEnd(name, nameColumnSize, ' ')
                        + Strings.padStart(totalWin, totalWinColumnSize, ' ')
                        + Strings.padStart(totalBet, totalBetColumnSize, ' ')
        );
    }
}
