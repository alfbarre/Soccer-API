package com.soccerapi.soccerapi.models.playerrequestv1;

import com.soccerapi.soccerapi.models.dto.Stats;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerRequest {

    private UUID playerId;

    @NotNull
    private String name;

    @NotNull
    private String age;

    @NotNull
    private String country;

    @NotNull
    private String team;

    @NotNull
    private String height;

    @NotNull
    private String active;

    @Valid
    private Stats stats;


}
