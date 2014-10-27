package com.github.lukaszkusek.roulette.service;

import com.github.lukaszkusek.roulette.dto.PlayerRequest;

public interface PlayerRequestConsumer {

    void consume(PlayerRequest playerRequest);
}
