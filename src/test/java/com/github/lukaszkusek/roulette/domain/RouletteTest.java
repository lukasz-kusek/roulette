package com.github.lukaszkusek.roulette.domain;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.github.lukaszkusek.roulette.util.IntStreams.executeTimes;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.intThat;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class RouletteTest {

    @Mock
    private RouletteListener rouletteListener;

    @InjectMocks
    private Roulette roulette;

    @Test
    public void shouldDrawBallAndPassToListener() {
        // when
        executeTimes(100).forEach(
                i -> roulette.drawBall()
        );

        // then
        then(rouletteListener).should(times(100)).ballDrawn(range(1, 36));
    }

    private static int range(int min, int max) {
        return intThat(new RangeMatcher(min, max));
    }

    private static class RangeMatcher extends BaseMatcher<Integer> {

        private int max;
        private int min;

        private RangeMatcher(int min, int max) {
            this.min = min;
            this.max = max;
        }

        @Override
        public boolean matches(Object o) {
            if (!(o instanceof Integer)) {
                return false;
            }
            Integer integer = (Integer) o;

            return integer >= min && integer <= max;
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("Int from <" + min + ", " + max + ">");
        }
    }
}
