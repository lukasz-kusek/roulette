package com.github.lukaszkusek.roulette.domain;

import com.github.lukaszkusek.roulette.domain.bets.Bet;
import com.github.lukaszkusek.roulette.domain.bets.outcome.BetOutcome;
import com.github.lukaszkusek.roulette.domain.bets.outcome.BetWithResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.data.MapEntry.entry;
import static com.github.lukaszkusek.roulette.domain.bets.outcome.BetOutcome.lose;
import static com.github.lukaszkusek.roulette.domain.bets.outcome.BetOutcome.win;
import static com.github.lukaszkusek.roulette.util.Assertions.assertThat;
import static com.github.lukaszkusek.roulette.util.BetResultBuilder.forBet;
import static com.github.lukaszkusek.roulette.util.Collections.list;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class GameTest {

    @Captor
    private ArgumentCaptor<Round> roundCaptor;

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
    private FinishedRoundListener finishedRoundListener;

    @InjectMocks
    private Game game;

    @Test
    public void shouldReportFinishedRoundWithPlacedBetsAndDrawnBall() {
        // given
        int drawnBall = 1;

        // when
        game.place(playerA, bet1);
        game.place(playerA, bet2);
        game.place(playerA, bet3);
        game.place(playerB, bet1);
        game.place(playerB, bet2);

        game.ballDrawn(drawnBall);

        // then
        then(finishedRoundListener).should().roundFinished(roundCaptor.capture());

        Round round = roundCaptor.getValue();

        assertThat(round)
                .hasDrawnBall(drawnBall)
                .hasPlayersBets(
                        entry(playerA, bet1),
                        entry(playerA, bet2),
                        entry(playerA, bet3),
                        entry(playerB, bet1),
                        entry(playerB, bet2));
    }

    @Test
    public void shouldStartNewRoundAfterEachDrawnBall() {
        // given
        int firstDrawnBall = 1;
        int secondDrawnBall = 2;

        // when
        game.place(playerA, bet1);

        game.ballDrawn(firstDrawnBall);

        game.place(playerB, bet2);

        game.ballDrawn(secondDrawnBall);

        // then
        then(finishedRoundListener).should(times(2)).roundFinished(roundCaptor.capture());

        List<Round> rounds = roundCaptor.getAllValues();

        assertThat(rounds.get(0))
                .hasDrawnBall(firstDrawnBall)
                .hasPlayersBets(entry(playerA, bet1));

        assertThat(rounds.get(1))
                .hasDrawnBall(secondDrawnBall)
                .hasPlayersBets(entry(playerB, bet2));
    }


    @Test
    public void shouldCalculateBetsOutcomes() {
        // given
        forBet(bet1).andDrawnBall(1).resultIs(win(4l));
        forBet(bet2).andDrawnBall(1).resultIs(lose());
        forBet(bet3).andDrawnBall(1).resultIs(win(36l));

        // when
        game.place(playerA, bet1);
        game.place(playerA, bet2);
        game.place(playerA, bet3);
        game.place(playerB, bet1);
        game.place(playerB, bet2);

        game.ballDrawn(1);

        // then
        then(finishedRoundListener).should().roundFinished(roundCaptor.capture());

        Round round = roundCaptor.getValue();

        assertThat(round).hasPlayersBetsWithResults(
                entry(playerA, list(
                        betWithResult(bet1, win(4l)),
                        betWithResult(bet2, lose()),
                        betWithResult(bet3, win(36l))
                )),
                entry(playerB, list(
                        betWithResult(bet1, win(4l)),
                        betWithResult(bet2, lose())
                ))
        );
    }

    private BetWithResult betWithResult(Bet bet, BetOutcome betOutcome) {
        return new BetWithResult(bet, betOutcome);
    }
}
