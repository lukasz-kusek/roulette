package com.github.lukaszkusek.roulette.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.data.MapEntry.entry;
import static com.github.lukaszkusek.roulette.domain.RoundAssert.assertThat;
import static com.github.lukaszkusek.roulette.util.BetResultBuilder.forBet;
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
        forBet(bet1).andDrawnBall(1).resultIs(Outcome.WIN, 4l);
        forBet(bet2).andDrawnBall(1).resultIs(Outcome.LOSE, 0l);
        forBet(bet3).andDrawnBall(1).resultIs(Outcome.WIN, 36l);

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
                entry(playerA, asList(
                        betWithResult(bet1, Outcome.WIN, 4l),
                        betWithResult(bet2, Outcome.LOSE, 0l),
                        betWithResult(bet3, Outcome.WIN, 36l)
                )),
                entry(playerB, asList(
                        betWithResult(bet1, Outcome.WIN, 4l),
                        betWithResult(bet2, Outcome.LOSE, 0l)
                ))
        );
    }

    private BetWithResult betWithResult(Bet bet, Outcome win, long winnings) {
        return new BetWithResult(bet, new BetResult(win, winnings));
    }
}
