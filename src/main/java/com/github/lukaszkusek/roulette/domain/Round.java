package com.github.lukaszkusek.roulette.domain;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Holds all bets within one round.
 * One round means between ball draws.
 */
public class Round {

    private Optional<Integer> drawnBall = Optional.empty();
    private final Multimap<Player, Bet> playersBets = ArrayListMultimap.create();
    private Map<Player, Collection<BetWithResult>> playersBetsWithResults;

    public void place(Player player, Bet bet) {
        Preconditions.checkState(!this.drawnBall.isPresent(), "Can't place bets after ball has been drawn.");
        playersBets.put(player, bet);
    }

    public void setDrawnBall(int drawnBall) {
        Preconditions.checkState(!this.drawnBall.isPresent(), "Can't set drawn ball twice.");
        this.drawnBall = Optional.of(drawnBall);
    }

    public Optional<Integer> getDrawnBall() {
        return drawnBall;
    }

    public Map<Player, Collection<BetWithResult>> getPlayersBetsWithResults() {
        return playersBetsWithResults;
    }

    public void finish() {
        Preconditions.checkState(this.drawnBall.isPresent(), "Can't finish round before ball has been drawn.");
        calculateOutcome();
    }

    private void calculateOutcome() {
        playersBetsWithResults = Maps.transformValues(
                playersBets.asMap(),
                bets -> bets.stream()
                            .map(bet ->
                                    new BetWithResult(
                                            bet,
                                            bet.calculateOutcome(drawnBall.get())))
                            .collect(Collectors.toList()));
    }

    @VisibleForTesting
    Multimap<Player, Bet> getPlayersBets() {
        return playersBets;
    }
}
