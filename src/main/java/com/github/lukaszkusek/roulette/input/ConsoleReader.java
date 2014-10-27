package com.github.lukaszkusek.roulette.input;

import com.github.lukaszkusek.roulette.dto.ConsolePlayerRequest;
import com.github.lukaszkusek.roulette.service.PlayerRequestConsumer;

import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConsoleReader {

    private final ExecutorService reader = Executors.newSingleThreadExecutor();

    private final Scanner consoleScanner;
    private final PlayerRequestConsumer playerRequestConsumer;

    public ConsoleReader(InputStream inputStream, PlayerRequestConsumer playerRequestConsumer) {
        this.consoleScanner = new Scanner(inputStream);
        this.playerRequestConsumer = playerRequestConsumer;
    }

    public void start() throws InterruptedException {
        reader.submit(
                (Runnable) () -> {
                    while (consoleScanner.hasNextLine()) {
                        try {
                            playerRequestConsumer.consume(new ConsolePlayerRequest(consoleScanner.nextLine()));
                        } catch (RuntimeException e) {
                            System.err.println(e.getMessage());
                        }
                    }
                }
        );
    }
}
