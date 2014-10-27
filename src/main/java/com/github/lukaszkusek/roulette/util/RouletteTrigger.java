package com.github.lukaszkusek.roulette.util;

import com.github.lukaszkusek.roulette.domain.Roulette;

public class RouletteTrigger implements Runnable {

    private final Roulette roulette;

    public RouletteTrigger(Roulette roulette) {
        this.roulette = roulette;
    }

    @Override
    public void run() {
        roulette.drawBall();
    }
}
