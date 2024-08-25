package com.soccerapi.soccerapi.models.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stats {

    @NotNull
    private String goals;

    @NotNull
    private String assists;

    @NotNull
    private String minutesPlayed;

    @NotNull
    private String averageMinutesPlayed;

    @NotNull
    private String fouls;

    @NotNull
    private String yellowCards;

    @NotNull
    private String redCards;
}
