CREATE DATABASE SOCCER;


CREATE TABLE PLAYER (
                        player_id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
                        name varchar(100) not null,
                        age varchar(3) not null,
                        country varchar(100) not null,
                        team varchar(100) not null,
                        height varchar(4) not null,
                        active varchar(5) not null

);

CREATE TABLE STATS (

                       stats_id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
                       goals varchar(4) not null,
                       assists varchar(4) not null,
                       minutes_played varchar(100) not null,
                       avg_minutes_played varchar(3) not null,
                       fouls varchar(100) not null,
                       yellow_cards varchar(100) not null,
                       red_cards varchar(100) not null,

                       FK_player_id UNIQUEIDENTIFIER FOREIGN KEY REFERENCES dbo.PLAYER(player_id)

);
