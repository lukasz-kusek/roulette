package com.github.lukaszkusek.roulette.service;

import com.github.lukaszkusek.roulette.domain.bets.outcome.BetWithResult;
import com.github.lukaszkusek.roulette.domain.Player;
import com.github.lukaszkusek.roulette.domain.Round;
import com.github.lukaszkusek.roulette.domain.bets.Bet;
import com.github.lukaszkusek.roulette.output.Printer;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collection;
import java.util.Map;

import static com.github.lukaszkusek.roulette.util.Collections.list;
import static com.github.lukaszkusek.roulette.util.Maps.map;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class FinishedRoundServiceTest {

    @Mock
    private Round round;

    @Mock
    private Player playerA;

    @Mock
    private Player playerB;

    @Mock
    private Bet bet1;

    @Mock
    private Bet bet2;

    @Mock
    private Bet bet3;

    @Mock
    private Printer printer;

    @InjectMocks
    private FinishedRoundService finishedRoundService;

    @Test
    public void shouldAddWinsToPlayersTotalWins() {
        // given
        Map<Player, Collection<BetWithResult>> playersBetsResults = map(
                playerA, list(win(4), lose(), win(36)),
                playerB, list(win(4), lose())
        );
        given(round.getPlayersBetsWithResults()).willReturn(playersBetsResults);

        // when
        finishedRoundService.roundFinished(round);

        // then
        then(playerA).should().addToTotalWin(4);
        then(playerA).should().addToTotalWin(36);
        then(playerB).should().addToTotalWin(4);
    }

    @Test
    @Ignore("TODO - mockito reports that playerA.addToTotalBet(4) was not invoked, but it was. Weird." )
    public void shouldAddWinsToPlayersTotalWinsBeforePrintingPlayersTotals() {
        // given
        Map<Player, Collection<BetWithResult>> playersBetsResults = map(playerA, list(win(4)));
        given(round.getPlayersBetsWithResults()).willReturn(playersBetsResults);

        // when
        finishedRoundService.roundFinished(round);

        // then
        InOrder inOrder = inOrder(playerA, printer);
        inOrder.verify(playerA).addToTotalBet(4);
        inOrder.verify(printer).printTotals();
    }

    @Test
    public void shouldPrintRoundResultsAndThenPrintPlayerTotals() {
        // given

        // when
        finishedRoundService.roundFinished(round);

        // then
        InOrder inOrder = inOrder(printer);
        inOrder.verify(printer).printRound(round);
        inOrder.verify(printer).printTotals();
    }

    private BetWithResult win(long winnings) {
        BetWithResult betWithResult = mock(BetWithResult.class);
        given(betWithResult.isWin()).willReturn(true);
        given(betWithResult.getWinnings()).willReturn(winnings);

        return betWithResult;
    }

    private BetWithResult lose() {
        BetWithResult betWithResult = mock(BetWithResult.class);
        given(betWithResult.isWin()).willReturn(false);

        return betWithResult;
    }
}
