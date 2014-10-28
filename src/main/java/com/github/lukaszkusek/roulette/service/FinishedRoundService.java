package com.github.lukaszkusek.roulette.service;

import com.github.lukaszkusek.roulette.domain.bets.outcome.BetWithResult;
import com.github.lukaszkusek.roulette.domain.FinishedRoundListener;
import com.github.lukaszkusek.roulette.domain.Round;
import com.github.lukaszkusek.roulette.output.Printer;

public class FinishedRoundService implements FinishedRoundListener {

    private Printer printer;

    public FinishedRoundService(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void roundFinished(Round finishedRound) {
        updatePlayersTotalWin(finishedRound);
        printer.printRound(finishedRound);
        printer.printTotals();
    }

    private void updatePlayersTotalWin(Round finishedRound) {
        finishedRound.getPlayersBetsWithResults().forEach(
                (player, bets) ->
                        bets.stream()
                            .filter(BetWithResult::isWin)
                            .forEach(bet -> player.addToTotalWin(bet.getWinnings()))
        );
    }

}
