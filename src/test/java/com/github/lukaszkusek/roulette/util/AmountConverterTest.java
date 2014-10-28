package com.github.lukaszkusek.roulette.util;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.Collection;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static com.github.lukaszkusek.roulette.util.Collections.list;
import static com.github.lukaszkusek.roulette.util.ThrowableCaptor.captureThrowable;

public class AmountConverterTest {

    @Test
    public void shouldConvertStringToLong() {
        // given
        Map<String, Long> values =
                ImmutableMap.<String, Long>builder()
                            .put("1", 100l)
                            .put("2", 200l)
                            .put("0.1", 10l)
                            .put("0.19", 19l)
                            .put("0.01", 1l)
                            .put("0.99", 99l)
                            .put("1.00", 100l)
                            .put("1.1", 110l)
                            .put("1.99", 199l)
                            .put(String.valueOf(Long.MAX_VALUE / 100), (Long.MAX_VALUE / 100) * 100)
                            .build();

        values.forEach((input, expected) -> {
            // when
            long converted = AmountConverter.convert(input);

            // then
            assertThat(converted).isEqualTo(expected);
        });
    }

    @Test
    public void shouldThrowAnExceptionOnInvalidAmount() {
        // given
        Collection<String> values = list(
                "A",
                "0",
                "-1",
                "1.000",
                "1.001",
                String.valueOf(Long.MAX_VALUE),
                String.valueOf(Long.MAX_VALUE / 100 + 1)
        );

        values.forEach((input) -> {
            // when
            Throwable throwable = captureThrowable(AmountConverter::convert, input);

            // then
            assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        });
    }

    @Test
    public void shouldConvertLongToString() {
        // given
        Map<Long, String> vales =
                ImmutableMap.of(
                        100l, "1.0",
                        0l, "0.0",
                        1l, "0.01",
                        10l, "0.1"
                );

        vales.forEach((input, expected) -> {
            // when
            String converted = AmountConverter.convert(input);

            // then
            assertThat(converted).isEqualTo(expected);
        });

    }
}
