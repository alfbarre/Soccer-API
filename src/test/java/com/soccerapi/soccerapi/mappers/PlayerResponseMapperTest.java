package com.soccerapi.soccerapi.mappers;

import com.soccerapi.soccerapi.models.dto.Stats;
import com.soccerapi.soccerapi.models.entity.Player;
import com.soccerapi.soccerapi.models.playerresponsev1.PlayerResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class PlayerResponseMapperTest {
    PlayerResponseMapper subject;

    @BeforeEach
    public void setUp() {
        subject = Mappers.getMapper(PlayerResponseMapper.class);
    }

    @Test
    void mapPlayerListToPlayerResponseList() {
        UUID playerId = UUID.randomUUID();

        List<Player> playerList = Collections.singletonList(Player.builder()
                .playerId(playerId)
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

        List<PlayerResponse> expectedPlayerResponseList = Collections.singletonList(PlayerResponse.builder()
                .playerId(playerId)
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

        List<PlayerResponse> actualPlayerResponseList = subject.mapPlayerListToPlayerResponseList(playerList);

        Assertions.assertEquals(expectedPlayerResponseList, actualPlayerResponseList);
    }

    @Test
    void mapPlayerToPlayerResponse(){
        Player player = Player.builder()
                .name("Name")
                .stats(com.soccerapi.soccerapi.models.entity.Stats.builder()
                        .goals("1")
                        .build())
                .build();

        PlayerResponse expectedPlayerResponse = PlayerResponse.builder()
                .name("Name")
                .stats(Stats.builder()
                        .goals("1")
                        .build())
                .build();

        PlayerResponse actualPlayerResponse = subject.mapPlayerToPlayerResponse(player);

        Assertions.assertEquals(expectedPlayerResponse, actualPlayerResponse);
    }

}
