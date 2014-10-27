package com.github.lukaszkusek.roulette.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @Test
    public void shouldAddAmountToTotalWin() {
        // given
        Player player = new Player("NAME");

        // when
        player.addToTotalWin(10);

        // then
        assertThat(player.getTotalWin()).isEqualTo(10);
    }

    @Test
    public void shouldAddAmountToTotalBet() {
        // given
        Player player = new Player("NAME");

        // when
        player.addToTotalBet(20);

        // then
        assertThat(player.getTotalBet()).isEqualTo(20);
    }

    @Test
    public void twoPlayersWithTheSameNameShouldBeEqual() {
        // given
        Player player1 = new Player("NAME");
        Player player2 = new Player("NAME");

        // when

        // then
        assertThat(player1).isEqualTo(player2);
    }

    @Test
    public void twoPlayersWithDifferentNamesShouldNotBeEqual() {
        // given
        Player player1 = new Player("NAME1");
        Player player2 = new Player("NAME2");

        // when

        // then
        assertThat(player1).isNotEqualTo(player2);
    }
}
