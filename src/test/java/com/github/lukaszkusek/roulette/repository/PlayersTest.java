package com.github.lukaszkusek.roulette.repository;

import com.github.lukaszkusek.roulette.domain.Player;
import com.github.lukaszkusek.roulette.util.ThrowableCaptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collection;

import static com.github.lukaszkusek.roulette.util.Assertions.assertThat;
import static com.github.lukaszkusek.roulette.util.Collections.list;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class PlayersTest {

    @Mock
    private FileReader fileReader;

    @Test
    public void shouldReturnPlayersByNames() {
        // given
        given(fileReader.readAll()).willReturn(list("NAME1", "NAME2" ));
        Players players = new Players(fileReader);

        // when
        Player player1 = players.get("NAME1");
        Player player2 = players.get("NAME2");

        // then
        assertThat(player1).hasName("NAME1").hasTotalWin(0).hasTotalBet(0);
        assertThat(player2).hasName("NAME2").hasTotalWin(0).hasTotalBet(0);
    }

    @Test
    public void shouldReturnAllPlayers() {
        // given
        given(fileReader.readAll()).willReturn(list("NAME1", "NAME2" ));
        Players players = new Players(fileReader);

        // when
        Collection<Player> allPlayers = players.getAll();

        // then
        assertThat(allPlayers).hasSize(2);
    }

    @Test
    public void shouldThrowAnExceptionInCaseOfNameDuplicates() {
        // given
        given(fileReader.readAll()).willReturn(list("NAME", "NAME" ));

        // when
        Throwable throwable = ThrowableCaptor.captureThrowable(Players::new, fileReader);

        // then
        assertThat(throwable).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void shouldThrowAnExceptionIfPlayerDoesntExist() {
        // given
        given(fileReader.readAll()).willReturn(list("NAME1", "NAME2" ));
        Players players = new Players(fileReader);

        // when
        Throwable throwable = ThrowableCaptor.captureThrowable(players::get, "ABC");

        // then
        assertThat(throwable).isInstanceOf(IllegalStateException.class);
    }

    @Test
    public void shouldReturnPlayersByNamesWithTotalWinAndBet() {
        // given
        given(fileReader.readAll()).willReturn(list("NAME1, 1, 2.0", "NAME2, 3.0, 0.15" ));
        Players players = new Players(fileReader);

        // when
        Player player1 = players.get("NAME1");
        Player player2 = players.get("NAME2");

        // then
        assertThat(player1).hasName("NAME1").hasTotalWin(100).hasTotalBet(200);
        assertThat(player2).hasName("NAME2").hasTotalWin(300).hasTotalBet(15);
    }
}
