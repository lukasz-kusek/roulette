package com.github.lukaszkusek.roulette.domain.factory;

import com.google.common.collect.ImmutableList;
import org.assertj.core.api.Assertions;
import com.github.lukaszkusek.roulette.domain.AbstractBet;
import com.github.lukaszkusek.roulette.domain.AbstractBetAssert;
import com.github.lukaszkusek.roulette.domain.Bet;
import com.github.lukaszkusek.roulette.domain.EvenBet;
import com.github.lukaszkusek.roulette.domain.OddBet;
import com.github.lukaszkusek.roulette.domain.StraightBet;
import com.github.lukaszkusek.roulette.domain.StraightBetAssert;
import org.junit.Test;

import java.util.Collection;

import static java.util.Arrays.asList;
import static com.github.lukaszkusek.roulette.domain.factory.BetFactoryTest.InputAndExpectedValues.forBet;
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
        Collection<String> bets = asList("ABC", "0", "-1", "37", "");

        bets.forEach(
                bet -> {
                    // when
                    Throwable throwable = captureThrowable(betFactory::from, bet, amount);

                    // then
                    Assertions.assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
                });
    }

    private static StraightBetAssert assertThat(StraightBet createdBet) {
        return StraightBetAssert.assertThat(createdBet);
    }

    private static AbstractBetAssert assertThat(Bet createdBet) {
        return AbstractBetAssert.assertThat((AbstractBet) createdBet);
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
