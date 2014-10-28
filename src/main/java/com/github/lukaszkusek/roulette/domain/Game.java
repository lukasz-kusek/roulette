package com.github.lukaszkusek.roulette.domain;

import com.github.lukaszkusek.roulette.domain.bets.Bet;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Represents roulette game. Holds current round and handles ball drawn events.
 */
public class Game implements RouletteListener {

    private final FinishedRoundListener finishedRoundListener;
    private final Lock lock = new ReentrantLock();

    private Round round = new Round();

    public Game(FinishedRoundListener finishedRoundListener) {
        this.finishedRoundListener = finishedRoundListener;
    }

    public void place(Player player, Bet bet) {
        lock.lock();

        try {
            round.place(player, bet);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void ballDrawn(int number) {
        Round finishedRound = getAndSetNewRound();

        finishedRound.setDrawnBall(number);
        finishedRound.finish();

        finishedRoundListener.roundFinished(finishedRound);
    }

    private Round getAndSetNewRound() {
        lock.lock();

        Round finishedRound;
        try {
            finishedRound = round;
            round = new Round();
        } finally {
            lock.unlock();
        }

        return finishedRound;
    }
}
