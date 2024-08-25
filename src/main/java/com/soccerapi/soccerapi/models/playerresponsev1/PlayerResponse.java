package com.soccerapi.soccerapi.models.playerresponsev1;

import com.soccerapi.soccerapi.models.dto.Stats;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerResponse {

    private UUID playerId;

    private String name;

    private String age;

    private String country;

    private String team;

    private String height;

    private String active;

    private Stats stats;
}
