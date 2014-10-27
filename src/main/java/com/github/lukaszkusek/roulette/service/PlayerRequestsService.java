package com.github.lukaszkusek.roulette.service;

import com.github.lukaszkusek.roulette.domain.Bet;
import com.github.lukaszkusek.roulette.domain.Game;
import com.github.lukaszkusek.roulette.domain.Player;
import com.github.lukaszkusek.roulette.domain.factory.BetFactory;
import com.github.lukaszkusek.roulette.dto.PlayerRequest;
import com.github.lukaszkusek.roulette.repository.Players;

/**
 * Executes players' requests. Places bets on a game.
 */
public class PlayerRequestsService {

    private final BetFactory betFactory;
    private final Players players;
    private final Game game;

    public PlayerRequestsService(BetFactory betFactory, Players players, Game game) {
        this.betFactory = betFactory;
        this.players = players;
        this.game = game;
    }

    public void execute(PlayerRequest playerRequest) {
        Bet bet = betFactory.from(playerRequest.getBet(), playerRequest.getAmount());
        Player player = players.get(playerRequest.getPlayerName());

        game.place(player, bet);
        player.addToTotalBet(playerRequest.getAmount());
    }
}
