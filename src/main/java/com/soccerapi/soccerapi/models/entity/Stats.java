package com.soccerapi.soccerapi.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "STATS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stats {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "stats_id")
    private UUID statsId;

    @Column(name = "goals")
    private String goals;

    @Column(name = "assists")
    private String assists;

    @Column(name = "minutes_played")
    private String minutesPlayed;

    @Column(name = "avg_minutes_played")
    private String averageMinutesPlayed;

    @Column(name = "fouls")
    private String fouls;

    @Column(name = "yellow_cards")
    private String yellowCards;

    @Column(name = "red_cards")
    private String redCards;

    @OneToOne
    @JoinColumn(name = "player_id",referencedColumnName = "player_id")
    private Player player;
}
