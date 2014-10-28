package com.github.lukaszkusek.roulette.domain.bets;

import com.google.common.collect.ImmutableList;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Collection;

import static com.github.lukaszkusek.roulette.domain.bets.BetFactoryTest.InputAndExpectedValues.forBet;
import static com.github.lukaszkusek.roulette.util.Assertions.assertThat;
import static com.github.lukaszkusek.roulette.util.Collections.list;
import static com.github.lukaszkusek.roulette.util.ThrowableCaptor.captureThrowable;

public class BetFactoryTest {

    private BetFactory betFactory = new BetFactory();

    @Test
    public void shouldCreateCorrectBets() {
        shouldCreateCorrectBets(
                forBet("1").andAmount(23).expectedTypeIs(StraightBet.class),
                forBet("10").andAmount(4).expectedTypeIs(StraightBet.class),
                forBet("36").andAmount(73).expectedTypeIs(StraightBet.class),
                forBet("ODD").andAmount(3).expectedTypeIs(OddBet.class),
                forBet("EVEN").andAmount(7).expectedTypeIs(EvenBet.class)
        );
    }

    private void shouldCreateCorrectBets(InputAndExpectedValues... values) {
        ImmutableList.copyOf(values).forEach(
                value -> {
                    // when
                    Bet createdBet = betFactory.from(value.getBet(), value.getAmount());

                    // then
                    assertThat(createdBet).isInstanceOf(value.getExpectedType())
                                          .hasAmount(value.getAmount());
                }
        );
    }

    @Test
    public void shouldCreateStraightBetWithCorrectNumber() {
        // given
        int number = 2;
        long amount = 100;

        // when
        Bet createdBet = betFactory.from(String.valueOf(number), amount);

        // then
        assertThat((StraightBet) createdBet).hasNumber(number);
    }

    @Test
    public void shouldThrowAnExceptionInCaseOfInvalidBet() {
        // given
        long amount = 1l;
        Collection<String> bets = list("ABC", "0", "-1", "37", "" );

        bets.forEach(
                bet -> {
                    // when
                    Throwable throwable = captureThrowable(betFactory::from, bet, amount);

                    // then
                    Assertions.assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
                });
    }

    static class InputAndExpectedValues {

        private String bet;
        private long amount;
        private Class<?> expectedType;

        private InputAndExpectedValues(String bet) {
            this.bet = bet;
        }

        static InputAndExpectedValues forBet(String bet) {
            return new InputAndExpectedValues(bet);
        }

        public InputAndExpectedValues andAmount(long amount) {
            this.amount = amount;
            return this;
        }

        public InputAndExpectedValues expectedTypeIs(Class<?> expectedType) {
            this.expectedType = expectedType;
            return this;
        }

        public String getBet() {
            return bet;
        }

        public long getAmount() {
            return amount;
        }

        public Class<?> getExpectedType() {
            return expectedType;
        }
    }
}
