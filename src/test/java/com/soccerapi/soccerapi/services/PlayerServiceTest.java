package com.soccerapi.soccerapi.services;

import com.soccerapi.soccerapi.exceptions.PlayerDoesNotExistException;
import com.soccerapi.soccerapi.mappers.PlayerRequestMapper;
import com.soccerapi.soccerapi.mappers.PlayerResponseMapper;
import com.soccerapi.soccerapi.models.dto.Stats;
import com.soccerapi.soccerapi.models.entity.Player;
import com.soccerapi.soccerapi.models.playerrequestv1.PlayerRequest;
import com.soccerapi.soccerapi.models.playerresponsev1.PlayerResponse;
import com.soccerapi.soccerapi.repository.PlayerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {


    @InjectMocks
    private PlayerService subject;
    @Mock
    private PlayerRepository mockPlayerRepository;
    @Mock
    private PlayerResponseMapper mockPlayerResponseMapper;
    @Mock
    private PlayerRequestMapper mockPlayerRequestMapper;

    private Player player;

    private PlayerResponse expectedPlayerResponse;

    private PlayerRequest playerRequest;


    @BeforeEach
    void setUp() {
        player = Player.builder()
                .name("Name")
                .age("21")
                .active("false")
                .stats(com.soccerapi.soccerapi.models.entity.Stats.builder()
                        .goals("11")
                        .assists("0")
                        .build())
                .build();

        expectedPlayerResponse = PlayerResponse.builder()
                .name("Name")
                .age("21")
                .active("false")
                .stats(Stats.builder()
                        .goals("11")
                        .assists("0")
                        .build())
                .build();

        playerRequest = PlayerRequest.builder()
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
    void getAllPlayersReturnsAllPlayers() {
        List<PlayerResponse> expectedPlayerResponseList = Collections.singletonList(PlayerResponse.builder()
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

        List<Player> playerList = Collections.singletonList(Player.builder()
                .playerId(UUID.randomUUID())
                .name("ABCName")
                .age("28")
                .active("true")
                .country("USA")
                .height("68")
                .stats(com.soccerapi.soccerapi.models.entity.Stats.builder()
                        .goals("58")
                        .assists("2")
                        .build())
                .build());

        when(mockPlayerRepository.findAll()).thenReturn(playerList);
        when(mockPlayerResponseMapper.mapPlayerListToPlayerResponseList(any())).thenReturn(expectedPlayerResponseList);
        List<PlayerResponse> actualPlayerResponseList = subject.getAllPlayers();

        Assertions.assertEquals(expectedPlayerResponseList, actualPlayerResponseList);
        verify(mockPlayerResponseMapper).mapPlayerListToPlayerResponseList(playerList);
    }

    @Test
    void addPlayerSuccessfullyAddsPlayer() {

        when(mockPlayerRequestMapper.mapPlayerRequestToPlayer(any())).thenReturn(player);
        when(mockPlayerRepository.save(any())).thenReturn(player);
        when(mockPlayerResponseMapper.mapPlayerToPlayerResponse(any())).thenReturn(expectedPlayerResponse);

        PlayerResponse actualPlayerResponse = subject.addPlayer(playerRequest);
        Assertions.assertEquals(expectedPlayerResponse, actualPlayerResponse);
        verify(mockPlayerRequestMapper).mapPlayerRequestToPlayer(playerRequest);
        verify(mockPlayerRepository).save(player);
        verify(mockPlayerResponseMapper).mapPlayerToPlayerResponse(player);

    }

    @Test
    void deletePlayerSuccessfullyDeletesPlayer() {
        UUID playerId = UUID.randomUUID();

        Optional<Player> optionalPlayer = Optional.of(player);
        when(mockPlayerRepository.findById(playerId)).thenReturn(optionalPlayer);

        subject.deletePlayer(playerId);
        verify(mockPlayerRepository).findById(playerId);
        verify(mockPlayerRepository).deleteById(playerId);

    }

    @Test
    void deletePlayerWhenPlayerNotFound() {
        UUID playerId = UUID.randomUUID();

        when(mockPlayerRepository.findById(playerId)).thenReturn(Optional.empty());
        Assertions.assertThrows(PlayerDoesNotExistException.class, () -> subject.deletePlayer(playerId));
        verify(mockPlayerRepository).findById(playerId);

    }

    @Test
    void findByPlayerIdSuccessfullyFindsPlayer() {
        UUID playerId = UUID.randomUUID();

        Optional<Player> optionalPlayer = Optional.of(player);
        when(mockPlayerRepository.findById(playerId)).thenReturn(optionalPlayer);
        when(mockPlayerResponseMapper.mapPlayerToPlayerResponse(any())).thenReturn(expectedPlayerResponse);

        PlayerResponse actualPlayerResponse = subject.findPlayerById(playerId);
        Assertions.assertEquals(expectedPlayerResponse, actualPlayerResponse);


        verify(mockPlayerRepository).findById(playerId);
        verify(mockPlayerResponseMapper).mapPlayerToPlayerResponse(player);

    }

    @Test
    void findByPlayerIdWhenPlayerNotFound() {
        UUID playerId = UUID.randomUUID();

        when(mockPlayerRepository.findById(playerId)).thenReturn(Optional.empty());
        Assertions.assertThrows(PlayerDoesNotExistException.class, () -> subject.findPlayerById(playerId));
        verify(mockPlayerRepository).findById(playerId);

    }

    @Test
    void updatePlayerSuccessfullyUpdatesPlayer() {
        Optional<Player> optionalPlayer = Optional.of(player);

        when(mockPlayerRepository.findById(any())).thenReturn(optionalPlayer);
        when(mockPlayerRequestMapper.mapPlayerRequestToPlayer(any())).thenReturn(player);
        when(mockPlayerRepository.save(any())).thenReturn(player);
        when(mockPlayerResponseMapper.mapPlayerToPlayerResponse(any())).thenReturn(expectedPlayerResponse);

        PlayerResponse actualPlayerResponse = subject.updatePlayer(playerRequest);

        Assertions.assertEquals(expectedPlayerResponse, actualPlayerResponse);
        verify(mockPlayerRepository).findById(playerRequest.getPlayerId());
        verify(mockPlayerRequestMapper).mapPlayerRequestToPlayer(playerRequest);
        verify(mockPlayerRepository).save(player);
        verify(mockPlayerResponseMapper).mapPlayerToPlayerResponse(player);

    }

    @Test
    void updatePlayerWhenPlayerNotFound() {
        when(mockPlayerRepository.findById(any())).thenReturn(Optional.empty());
        Assertions.assertThrows(PlayerDoesNotExistException.class, () -> subject.updatePlayer(playerRequest));
        verify(mockPlayerRepository).findById(playerRequest.getPlayerId());

    }

    @Test
    void deleteAllPlayersSuccessfullyDeletesAllPlayers() {
        subject.deleteAllPlayers();
        verify(mockPlayerRepository).deleteAll();

    }
}