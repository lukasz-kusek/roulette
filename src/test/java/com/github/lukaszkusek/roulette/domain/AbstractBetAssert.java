package com.github.lukaszkusek.roulette.domain;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.internal.Objects;

public class AbstractBetAssert extends AbstractAssert<AbstractBetAssert, AbstractBet> {

    private Objects objects = Objects.instance();

    private AbstractBetAssert(AbstractBet actual) {
        super(actual, AbstractBetAssert.class);
    }

    public static AbstractBetAssert assertThat(AbstractBet actual) {
        return new AbstractBetAssert(actual);
    }

    public AbstractBetAssert hasAmount(long expected) {
        isNotNull();
        objects.assertEqual(info, actual.getAmount(), expected);
        return this;
    }
}
