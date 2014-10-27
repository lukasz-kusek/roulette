package com.github.lukaszkusek.roulette.util;

import com.github.lukaszkusek.roulette.domain.Roulette;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;

@RunWith(MockitoJUnitRunner.class)
public class RouletteTriggerTest {

    @Mock
    private Roulette roulette;

    @InjectMocks
    private RouletteTrigger rouletteTrigger;

    @Test
    public void shouldBeInstanceOfRunnable() {
        // then
        assertThat(rouletteTrigger).isInstanceOf(Runnable.class);
    }

    @Test
    public void shouldDrawBallOnRun() {
        // when
        rouletteTrigger.run();

        // then
        then(roulette).should().drawBall();
    }
}
