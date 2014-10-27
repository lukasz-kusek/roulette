package com.github.lukaszkusek.roulette.output;

import com.github.lukaszkusek.roulette.domain.Round;
import com.github.lukaszkusek.roulette.repository.Players;

public class ConsolePrinter implements Printer {

    private PlayersConsolePrinter playersConsolePrinter;

    public ConsolePrinter(Players players) {
        this.playersConsolePrinter = new PlayersConsolePrinter(players);
    }

    @Override
    public void printRound(Round round) {
        new RoundConsolePrinter(round).printRound();
    }

    @Override
    public void printTotals() {
        playersConsolePrinter.printTotals();
    }
}
