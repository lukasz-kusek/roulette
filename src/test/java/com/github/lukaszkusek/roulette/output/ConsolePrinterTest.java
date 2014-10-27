package com.github.lukaszkusek.roulette.output;

import com.github.lukaszkusek.roulette.domain.Bet;
import com.github.lukaszkusek.roulette.domain.BetWithResult;
import com.github.lukaszkusek.roulette.domain.Outcome;
import com.github.lukaszkusek.roulette.domain.Player;
import com.github.lukaszkusek.roulette.domain.Round;
import com.github.lukaszkusek.roulette.repository.Players;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static com.github.lukaszkusek.roulette.domain.Outcome.WIN;
import static com.github.lukaszkusek.roulette.util.Maps.map;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class ConsolePrinterTest {

    private ByteArrayOutputStream outContent;

    @Mock
    private Round round;

    @Mock
    private Players players;

    @InjectMocks
    private ConsolePrinter consolePrinter;

    @Before
    public void redirectStandardOutput() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void shouldPrintRound() {
        // given
        Player player = createPlayer("NAME");
        BetWithResult betWithResult = createBetWithResult("ODD", WIN, 3600l);

        given(round.getPlayersBetsWithResults()).willReturn(map(player, asList(betWithResult)));
        given(round.getDrawnBall()).willReturn(Optional.of(2));

        // when
        consolePrinter.printRound(round);

        // then
        assertThat(outContent.toString())
                .contains("Number: 2")
                .contains("Player").contains("Bet").contains("Outcome").contains("Winnings")
                .contains("NAME").contains("ODD").contains("WIN").contains("36.0");
    }

    @Test
    public void shouldPrintTotals() {
        // given
        Player player = createPlayer("NAME", 1000l, 4500l);

        given(players.getAll()).willReturn(asList(player));

        // when
        consolePrinter.printTotals();

        // then
        assertThat(outContent.toString())
                .contains("Player").contains("Total Win").contains("Total Bet")
                .contains("NAME").contains("10.0").contains("45.0");
    }

    private Player createPlayer(String name) {
        return createPlayer(name, 0, 0);
    }

    private Player createPlayer(String name, long totalWin, long totalBet) {
        Player player = mock(Player.class);
        given(player.getName()).willReturn(name);
        given(player.getTotalWin()).willReturn(totalWin);
        given(player.getTotalBet()).willReturn(totalBet);

        return player;
    }

    private BetWithResult createBetWithResult(String betType, Outcome outcome, long winnings) {
        BetWithResult betWithResult = mock(BetWithResult.class);
        Bet bet = mock(Bet.class);
        given(bet.toString()).willReturn(betType);
        given(betWithResult.getBet()).willReturn(bet);
        given(betWithResult.getOutcome()).willReturn(outcome);
        given(betWithResult.getWinnings()).willReturn(winnings);

        return betWithResult;
    }


}
