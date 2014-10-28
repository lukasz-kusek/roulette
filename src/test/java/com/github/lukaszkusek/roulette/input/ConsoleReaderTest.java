package com.github.lukaszkusek.roulette.input;

import com.github.lukaszkusek.roulette.dto.PlayerRequest;
import com.github.lukaszkusek.roulette.service.PlayerRequestConsumer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static com.github.lukaszkusek.roulette.util.Assertions.assertThat;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.timeout;

@RunWith(MockitoJUnitRunner.class)
public class ConsoleReaderTest {

    @Captor
    private ArgumentCaptor<PlayerRequest> playerRequestCaptor;

    @Mock
    private PlayerRequestConsumer playerRequestConsumer;

    @Test
    public void shouldPassPlayerRequestToConsumer() throws InterruptedException {
        // given
        String input = "NAME 1 1.0";
        InputStream inputStream = createInputStream(input, input);

        // when
        new ConsoleReader(inputStream, playerRequestConsumer).start();

        // then
        then(playerRequestConsumer).should(timeout(500).times(2)).consume(playerRequestCaptor.capture());

        assertThat(playerRequestCaptor.getValue())
                .hasPlayerName("NAME")
                .hasBet("1")
                .hasAmount(100);
    }

    private InputStream createInputStream(String... inputs) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String input : inputs) {
            stringBuilder.append(input).append(System.lineSeparator());
        }

        return new ByteArrayInputStream(stringBuilder.toString().getBytes());
    }

}
