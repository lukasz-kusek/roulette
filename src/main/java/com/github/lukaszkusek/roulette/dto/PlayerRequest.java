package com.github.lukaszkusek.roulette.dto;

/**
 * Holds data passed from player.
 */
public interface PlayerRequest {

    String getPlayerName();

    String getBet();

    long getAmount();
}
