package com.github.lukaszkusek.roulette.service;

import com.github.lukaszkusek.roulette.dto.PlayerRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.ExecutorService;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class PlayerRequestsExecutorTest {

    @Captor
    private ArgumentCaptor<Runnable> runnableCaptor;

    @Mock
    private ExecutorService executorService;

    @Mock
    private PlayerRequestsService playerRequestsService;

    @InjectMocks
    private PlayerRequestRequestsExecutor playerRequestsExecutor;

    @Test
    public void shouldExecutePlayerRequest() {
        // given
        PlayerRequest playerRequest = mock(PlayerRequest.class);


        // when
        playerRequestsExecutor.consume(playerRequest);

        // then
        then(executorService).should().submit(runnableCaptor.capture());

        runnableCaptor.getValue().run();

        then(playerRequestsService).should().execute(playerRequest);
    }
}
