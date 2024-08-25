package com.soccerapi.soccerapi.mappers;

import com.soccerapi.soccerapi.models.entity.Player;
import com.soccerapi.soccerapi.models.playerresponsev1.PlayerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",nullValueMapMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface PlayerResponseMapper {
    List<PlayerResponse> mapPlayerListToPlayerResponseList(List<Player> playerList);

    @Mapping(target = "stats", source = "stats")
    PlayerResponse mapPlayerToPlayerResponse(Player player);
}




