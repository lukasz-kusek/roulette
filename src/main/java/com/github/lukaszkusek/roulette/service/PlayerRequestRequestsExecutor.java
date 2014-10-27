package com.github.lukaszkusek.roulette.service;

import com.github.lukaszkusek.roulette.dto.PlayerRequest;

import java.util.concurrent.ExecutorService;

public class PlayerRequestRequestsExecutor implements PlayerRequestConsumer {

    private final ExecutorService executorService;
    private final PlayerRequestsService playerRequestsService;

    public PlayerRequestRequestsExecutor(ExecutorService executorService, PlayerRequestsService playerRequestsService) {
        this.executorService = executorService;
        this.playerRequestsService = playerRequestsService;
    }

    @Override
    public void consume(PlayerRequest playerRequest) {
        executorService.submit(() -> {
            try {
                playerRequestsService.execute(playerRequest);
            } catch (RuntimeException e) {
                System.err.println(e.getMessage());
            }
        });
    }
}
