package com.soccerapi.soccerapi.controllers;

import com.soccerapi.soccerapi.models.dto.Stats;
import com.soccerapi.soccerapi.models.playerrequestv1.PlayerRequest;
import com.soccerapi.soccerapi.models.playerresponsev1.PlayerResponse;
import com.soccerapi.soccerapi.services.PlayerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerControllerTest {

    @InjectMocks
    private PlayerController subject;

    @Mock
    private PlayerService mockPlayerService;

    private PlayerResponse expectedPlayerResponse;

    private PlayerRequest expectedPlayerRequest;

    @BeforeEach
    void setUp() {

        expectedPlayerResponse = PlayerResponse.builder()
                .name("Name")
                .age("21")
                .active("false")
                .stats(Stats.builder()
                        .goals("11")
                        .assists("0")
                        .build())
                .build();

        expectedPlayerRequest = PlayerRequest.builder()
                .name("Name")
                .age("21")
                .active("false")
                .stats(Stats.builder()
                        .goals("11")
                        .assists("0")
                        .build())
                .build();
    }

    @Test
    void getAllPlayers() {
        List<PlayerResponse> expectedPlayerResponseList = asList(PlayerResponse.builder()
                .playerId(UUID.randomUUID())
                .name("ABCName")
                .age("28")
                .active("true")
                .country("USA")
                .height("68")
                .stats(Stats.builder()
                        .goals("58")
                        .assists("2")
                        .build())
                .build());

        when(mockPlayerService.getAllPlayers()).thenReturn(expectedPlayerResponseList);
        List<PlayerResponse> actualPlayerResponseList = subject.getAllPlayers();
        Assertions.assertEquals(expectedPlayerResponseList, actualPlayerResponseList);
    }

    @Test
    void addPlayers() {

        when(mockPlayerService.addPlayer(any())).thenReturn(expectedPlayerResponse);
        PlayerResponse actualPlayerResponse = subject.addPlayers(expectedPlayerRequest);
        Assertions.assertEquals(expectedPlayerResponse, actualPlayerResponse);
        verify(mockPlayerService).addPlayer(expectedPlayerRequest);
    }

    @Test
    void deletePlayer() {
        UUID playerId = UUID.randomUUID();
        subject.deletePlayer(playerId);
        verify(mockPlayerService).deletePlayer(playerId);
    }

    @Test
    void getPlayer() {
        UUID playerId = UUID.randomUUID();
        when(mockPlayerService.findPlayerById(playerId)).thenReturn(expectedPlayerResponse);
        PlayerResponse actualPlayerResponse = subject.getPlayer(playerId);
        Assertions.assertEquals(expectedPlayerResponse, actualPlayerResponse);
        verify(mockPlayerService).findPlayerById(playerId);
    }

    @Test
    void updatePlayer() {
        when(mockPlayerService.updatePlayer(any())).thenReturn(expectedPlayerResponse);
        PlayerResponse actualPlayerResponse = subject.updatePlayer(expectedPlayerRequest);
        Assertions.assertEquals(expectedPlayerResponse, actualPlayerResponse);
        verify(mockPlayerService).updatePlayer(expectedPlayerRequest);
    }

    @Test
    void deleteAllPlayers() {
        subject.deleteAllPlayers();
        verify(mockPlayerService).deleteAllPlayers();
    }


}