package com.github.lukaszkusek.roulette.repository;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static com.github.lukaszkusek.roulette.util.Collections.list;

public class FileReaderTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void shouldReadLinesFromFile() throws IOException {
        // given
        Path path = folder.newFile().toPath();
        Files.write(path, list("A", "B" ), StandardOpenOption.WRITE);

        // when
        FileReader fileReader = new FileReader(path);
        Collection<String> read = fileReader.readAll();

        // then
        assertThat(read).containsExactly("A", "B");
    }
}
