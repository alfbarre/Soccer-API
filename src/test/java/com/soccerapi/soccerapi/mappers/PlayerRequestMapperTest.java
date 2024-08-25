package com.soccerapi.soccerapi.mappers;

import com.soccerapi.soccerapi.models.dto.Stats;
import com.soccerapi.soccerapi.models.entity.Player;
import com.soccerapi.soccerapi.models.playerrequestv1.PlayerRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

public class PlayerRequestMapperTest {


    @Test
    void mapPlayerRequestToPlayer() {

        PlayerRequestMapper subject = Mappers.getMapper(PlayerRequestMapper.class);

        PlayerRequest playerRequest = PlayerRequest.builder()
                .name("Name")
                .stats(Stats.builder()
                        .goals("5")
                        .build())
                .build();

        Player expectedPlayer = Player.builder()
                .name("Name")
                .stats(com.soccerapi.soccerapi.models.entity.Stats.builder()
                        .goals("5")
                        .build())
                .build();

        Player actualPlayer = subject.mapPlayerRequestToPlayer(playerRequest);

        Assertions.assertEquals(expectedPlayer, actualPlayer);
    }
}
