package com.github.lukaszkusek.roulette.dto;

import com.google.common.collect.ImmutableList;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import static com.github.lukaszkusek.roulette.dto.ConsolePlayerRequestTest.InputAndExpectedValues.playerRequest;
import static com.github.lukaszkusek.roulette.dto.PlayerRequestAssert.assertThat;
import static com.github.lukaszkusek.roulette.util.ThrowableCaptor.captureThrowable;

public class ConsolePlayerRequestTest {

    @Test
    public void shouldCreateCorrectPlayerRequests() {
        shouldCreateCorrectPlayerRequests(
                playerRequest().createdFromLine("NAME 1 1.0")
                               .shouldHave()
                               .playerName("NAME")
                               .bet("1")
                               .amount(100),

                playerRequest().createdFromLine("NAME1 1 2.0")
                               .shouldHave()
                               .playerName("NAME1")
                               .bet("1")
                               .amount(200),

                playerRequest().createdFromLine("   NAME    1    1.0   ")
                               .shouldHave()
                               .playerName("NAME")
                               .bet("1")
                               .amount(100),

                playerRequest().createdFromLine("NAME ODD 1.0")
                               .shouldHave()
                               .playerName("NAME")
                               .bet("ODD")
                               .amount(100),

                playerRequest().createdFromLine("NAME EVEN 1.0")
                               .shouldHave()
                               .playerName("NAME")
                               .bet("EVEN")
                               .amount(100)
        );
    }

    private void shouldCreateCorrectPlayerRequests(InputAndExpectedValues... values) {
        ImmutableList.copyOf(values).forEach(
                value -> {
                    // when
                    PlayerRequest playerRequest = new ConsolePlayerRequest(value.getLine());

                    // then
                    assertThat(playerRequest)
                            .hasPlayerName(value.getPlayerName())
                            .hasBet(value.getBet())
                            .hasAmount(value.getAmount());
                });
    }

    @Test
    public void shouldThrowAnExceptionInCaseOfInvalidPlayerRequests() {
        shouldThrowAnExceptionInCaseOfInvalidPlayerRequests(
                "NAME",
                "1 1.0",
                "NAME 1.0",
                "NAME 1",
                "NAME 1 ABC"
        );
    }

    private void shouldThrowAnExceptionInCaseOfInvalidPlayerRequests(String... values) {
        ImmutableList.copyOf(values).forEach(
                input -> {
                    // when
                    Throwable throwable = captureThrowable(ConsolePlayerRequest::new, input);

                    // then
                    Assertions.assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
                });
    }

    static class InputAndExpectedValues {

        private String line;
        private String playerName;
        private String bet;
        private int amount;

        static InputAndExpectedValues playerRequest() {
            return new InputAndExpectedValues();
        }

        public InputAndExpectedValues createdFromLine(String line) {
            this.line = line;
            return this;
        }

        public InputAndExpectedValues shouldHave() {
            return this;
        }

        public InputAndExpectedValues playerName(String playerName) {
            this.playerName = playerName;
            return this;
        }

        public InputAndExpectedValues bet(String bet) {
            this.bet = bet;
            return this;
        }

        public InputAndExpectedValues amount(int amount) {
            this.amount = amount;
            return this;
        }

        public String getLine() {
            return line;
        }

        public String getPlayerName() {
            return playerName;
        }

        public String getBet() {
            return bet;
        }

        public int getAmount() {
            return amount;
        }
    }
}
