package com.github.lukaszkusek.roulette.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

public class FileReader {

    private final Collection<String> lines;

    public FileReader(Path path) throws IOException {
        this.lines = Files.readAllLines(path);
    }

    public Collection<String> read() {
        return lines;
    }
}
