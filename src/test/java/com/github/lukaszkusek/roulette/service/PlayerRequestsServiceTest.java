package com.github.lukaszkusek.roulette.service;

import com.github.lukaszkusek.roulette.domain.bets.Bet;
import com.github.lukaszkusek.roulette.domain.Game;
import com.github.lukaszkusek.roulette.domain.Player;
import com.github.lukaszkusek.roulette.domain.bets.BetFactory;
import com.github.lukaszkusek.roulette.dto.ConsolePlayerRequest;
import com.github.lukaszkusek.roulette.repository.Players;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class PlayerRequestsServiceTest {

    private static final String PLAYER_NAME = "NAME";
    private static final String BET = "EVEN";
    private static final long AMOUNT = 110;

    @Mock
    private Bet bet;

    @Mock
    private Player player;

    @Mock
    private BetFactory betFactory;

    @Mock
    private Players players;

    @Mock
    private Game game;

    @InjectMocks
    private PlayerRequestsService playerRequestsService;

    @Before
    public void setupBetFactory() {
        given(betFactory.from(BET, AMOUNT)).willReturn(bet);
    }

    @Before
    public void setupPlayers() {
        given(players.get(PLAYER_NAME)).willReturn(player);
    }

    @Test
    public void shouldPlaceBetAndThenAddBetToPlayersTotal() {
        // given
        ConsolePlayerRequest playerRequest = create(PLAYER_NAME, BET, AMOUNT);

        // when
        playerRequestsService.execute(playerRequest);

        // then
        InOrder inOrder = inOrder(game, player);
        inOrder.verify(game).place(player, bet);
        inOrder.verify(player).addToTotalBet(AMOUNT);
    }

    private ConsolePlayerRequest create(String playerName, String bet, long amount) {
        ConsolePlayerRequest playerRequest = mock(ConsolePlayerRequest.class);
        given(playerRequest.getPlayerName()).willReturn(playerName);
        given(playerRequest.getBet()).willReturn(bet);
        given(playerRequest.getAmount()).willReturn(amount);

        return playerRequest;
    }
}
