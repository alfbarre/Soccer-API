package com.soccerapi.soccerapi.mappers;

import com.soccerapi.soccerapi.models.entity.Player;
import com.soccerapi.soccerapi.models.playerrequestv1.PlayerRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(componentModel = "spring",nullValueMapMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface PlayerRequestMapper {

    @Mapping(target = "stats", source = "stats")
    Player mapPlayerRequestToPlayer(PlayerRequest playerRequest);
}
