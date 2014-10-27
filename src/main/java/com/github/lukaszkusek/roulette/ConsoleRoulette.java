package com.github.lukaszkusek.roulette;

import com.github.lukaszkusek.roulette.domain.FinishedRoundListener;
import com.github.lukaszkusek.roulette.domain.Game;
import com.github.lukaszkusek.roulette.domain.Roulette;
import com.github.lukaszkusek.roulette.domain.factory.BetFactory;
import com.github.lukaszkusek.roulette.input.ConsoleReader;
import com.github.lukaszkusek.roulette.output.ConsolePrinter;
import com.github.lukaszkusek.roulette.output.Printer;
import com.github.lukaszkusek.roulette.repository.FileReader;
import com.github.lukaszkusek.roulette.repository.Players;
import com.github.lukaszkusek.roulette.service.FinishedRoundService;
import com.github.lukaszkusek.roulette.service.PlayerRequestRequestsExecutor;
import com.github.lukaszkusek.roulette.service.PlayerRequestsService;
import com.github.lukaszkusek.roulette.util.RouletteTrigger;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Main class for the roulette game.
 * Requires path to a file with players as an argument.
 */
public class ConsoleRoulette {

    public static final int ROULETTE_TRIGGER_DELAY = 30;
    public static final int MAX_CONCURRENT_PLAYERS = 10;

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println();
        System.out.println("#########################################################################################");
        System.out.println("#################################  Roulette  ############################################");
        System.out.println("#########################################################################################");
        System.out.println();

        if (args.length != 1) {
            System.err.println("You need to provide a path to a file with players.");
            System.exit(1);
        }

        Players players = createPlayers(args[0]);
        Printer consolePrinter = new ConsolePrinter(players);
        FinishedRoundListener finishedRoundService = new FinishedRoundService(consolePrinter);
        Game game = new Game(finishedRoundService);
        Roulette roulette = new Roulette(game);
        RouletteTrigger rouletteTrigger = new RouletteTrigger(roulette);
        BetFactory betFactory = new BetFactory();
        PlayerRequestsService playerRequestsService = new PlayerRequestsService(betFactory, players, game);
        PlayerRequestRequestsExecutor playerRequestsExecutor =
                new PlayerRequestRequestsExecutor(createThreadPool(), playerRequestsService);
        ConsoleReader consoleReader = new ConsoleReader(System.in, playerRequestsExecutor);

        startRouletteTrigger(rouletteTrigger);
        consoleReader.start();
    }

    private static ExecutorService createThreadPool() {
        return Executors.newFixedThreadPool(MAX_CONCURRENT_PLAYERS);
    }

    private static Players createPlayers(String arg) throws IOException {
        return new Players(new FileReader(FileSystems.getDefault().getPath(arg)));
    }

    private static void startRouletteTrigger(RouletteTrigger rouletteTrigger) {
        Executors.newScheduledThreadPool(1)
                 .scheduleWithFixedDelay(
                         rouletteTrigger, ROULETTE_TRIGGER_DELAY, ROULETTE_TRIGGER_DELAY, TimeUnit.SECONDS);
    }
}
