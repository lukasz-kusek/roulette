package com.github.lukaszkusek.roulette.output;

import com.google.common.base.Strings;
import com.github.lukaszkusek.roulette.domain.BetWithResult;
import com.github.lukaszkusek.roulette.domain.Player;
import com.github.lukaszkusek.roulette.domain.Round;
import com.github.lukaszkusek.roulette.util.AmountConverter;

import java.util.Collection;
import java.util.Comparator;

class RoundConsolePrinter {

    private static final int BET_COLUMN_SIZE = 4;
    private static final int OUTCOME_COLUMN_SIZE = 8;
    private static final int MINIMAL_WINNINGS_COLUMN_SIZE = 9;
    private static final int MINIMAL_NAME_COLUMN_SIZE = 7;

    private final Round round;
    private final int nameColumnSize;
    private final int winningsColumnSize;

    RoundConsolePrinter(Round round) {
        this.round = round;

        nameColumnSize =
                round.getPlayersBetsWithResults()
                     .keySet()
                     .stream()
                     .map(Player::getName)
                     .map(String::length)
                     .max(Comparator.<Integer>naturalOrder())
                     .map(size -> size < MINIMAL_NAME_COLUMN_SIZE ? MINIMAL_NAME_COLUMN_SIZE : size + 1)
                     .orElse(MINIMAL_NAME_COLUMN_SIZE);

        winningsColumnSize =
                round.getPlayersBetsWithResults()
                     .values()
                     .stream()
                     .flatMap(Collection::stream)
                     .map(BetWithResult::getWinnings)
                     .map(String::valueOf)
                     .map(String::length)
                     .max(Comparator.<Integer>naturalOrder())
                     .map(size -> size < MINIMAL_WINNINGS_COLUMN_SIZE ? MINIMAL_WINNINGS_COLUMN_SIZE : size + 1)
                     .orElse(MINIMAL_WINNINGS_COLUMN_SIZE);
    }

    void printRound() {
        System.out.println();
        System.out.println("Number: " + round.getDrawnBall().get());

        printResultsLine("Player", "Bet", "Outcome", "Winnings");

        round.getPlayersBetsWithResults().forEach(
                (player, bets) ->
                        bets.forEach(
                                betWithResult ->
                                        printBetResults(player, betWithResult)
                        )
        );

        System.out.println();
    }

    private void printBetResults(Player player, BetWithResult betWithResult) {
        printResultsLine(
                player.getName(),
                betWithResult.getBet().toString(),
                betWithResult.getOutcome().name(),
                AmountConverter.convert(betWithResult.getWinnings()));
    }

    private void printResultsLine(String name, String bet, String outcome, String winnings) {
        System.out.println(
                Strings.padEnd(name, nameColumnSize, ' ')
                        + Strings.padStart(bet, BET_COLUMN_SIZE, ' ')
                        + Strings.padStart(outcome, OUTCOME_COLUMN_SIZE, ' ')
                        + Strings.padStart(winnings, winningsColumnSize, ' ')
        );
    }
}
