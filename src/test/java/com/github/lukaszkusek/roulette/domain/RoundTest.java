package com.github.lukaszkusek.roulette.domain;

import org.assertj.core.api.Assertions;
import com.github.lukaszkusek.roulette.util.ThrowableCaptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.data.MapEntry.entry;
import static com.github.lukaszkusek.roulette.domain.RoundAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class RoundTest {

    @Mock
    private Player player;

    @Mock
    private Bet bet;

    @Test
    public void shouldStorePlayersBet() {
        // given
        Round round = new Round();

        // when
        round.place(player, bet);

        // then
        assertThat(round).hasPlayersBets(entry(player, bet));
    }

    @Test
    public void shouldStoreDrawnBall() {
        // given
        Round round = new Round();

        // when
        round.setDrawnBall(1);

        // then
        assertThat(round).hasDrawnBall(1);
    }

    @Test
    public void shouldNotBePossibleToPlaceBetAfterDrawnBallHasBeenSet() {
        // given
        Round round = new Round();
        round.setDrawnBall(1);

        // when
        Throwable throwable = ThrowableCaptor.captureThrowable(round::place, player, bet);

        // then
        Assertions.assertThat(throwable).isInstanceOf(IllegalStateException.class);
    }

    @Test
    public void shouldNotBePossibleToSetDrawnBallAfterDrawnBallHasBeenAlreadySet() {
        // given
        Round round = new Round();
        round.setDrawnBall(1);

        // when
        Throwable throwable = ThrowableCaptor.captureThrowable(round::setDrawnBall, 2);

        // then
        Assertions.assertThat(throwable).isInstanceOf(IllegalStateException.class);
    }
}
