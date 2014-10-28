package com.github.lukaszkusek.roulette.repository;

import com.github.lukaszkusek.roulette.domain.Player;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Repository holding all player instances.
 */
public class Players {

    private final Map<String, Player> players;

    public Players(FileReader fileReader) {
        players = fileReader.readAll()
                            .stream()
                            .map(Player::new)
                            .collect(Collectors.toMap(Player::getName, player -> player));
    }

    public Player get(String playerName) {
        Player player = players.get(playerName);

        if (player == null) {
            throw new IllegalStateException("Player '" + playerName + "' doesn't exist.");
        }
        return player;
    }

    public Collection<Player> getAll() {
        return players.values();
    }
}
