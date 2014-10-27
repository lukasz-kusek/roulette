package com.github.lukaszkusek.roulette.domain;

import java.util.Random;

/**
 * Represents roulette which is responsible for drawing a ball.
 */
public class Roulette {

    public static final int MAX_NUMBER = 36;

    private final Random random = new Random();
    private final RouletteListener rouletteListener;

    public Roulette(RouletteListener rouletteListener) {
        this.rouletteListener = rouletteListener;
    }

    public void drawBall() {
        rouletteListener.ballDrawn(random.nextInt(MAX_NUMBER) + 1);
    }
}
