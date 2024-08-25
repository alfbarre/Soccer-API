package com.soccerapi.soccerapi.services;

import com.soccerapi.soccerapi.exceptions.PlayerDoesNotExistException;
import com.soccerapi.soccerapi.mappers.PlayerRequestMapper;
import com.soccerapi.soccerapi.mappers.PlayerResponseMapper;
import com.soccerapi.soccerapi.models.entity.Player;
import com.soccerapi.soccerapi.models.playerrequestv1.PlayerRequest;
import com.soccerapi.soccerapi.models.playerresponsev1.PlayerResponse;
import com.soccerapi.soccerapi.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerResponseMapper playerResponseMapper;
    private final PlayerRequestMapper playerRequestMapper;


    public List<PlayerResponse> getAllPlayers() {
        List<Player> playerList = playerRepository.findAll();
        return playerResponseMapper.mapPlayerListToPlayerResponseList(playerList);

    }

    public PlayerResponse addPlayer(PlayerRequest playerRequest) {
        Player player = playerRequestMapper.mapPlayerRequestToPlayer(playerRequest);
        player.getStats().setPlayer(player);
        Player player2 = playerRepository.save(player);
        return playerResponseMapper.mapPlayerToPlayerResponse(player2);
    }

    public void deletePlayer(UUID playerId) {
        Optional<Player> player = playerRepository.findById(playerId);
        if (player.isEmpty()) {
            throw new PlayerDoesNotExistException("Player with id " + playerId + " does not exist.");
        }
        playerRepository.deleteById(playerId);
    }

    public PlayerResponse findPlayerById(UUID playerId) {
        Optional<Player> player = playerRepository.findById(playerId);
        if (player.isEmpty()) {
            throw new PlayerDoesNotExistException("Player with id " + playerId + " does not exist.");
        }

        return playerResponseMapper.mapPlayerToPlayerResponse(player.get());
    }

    public PlayerResponse updatePlayer(PlayerRequest playerRequest) {
        Optional<Player> player = playerRepository.findById(playerRequest.getPlayerId());

        if (player.isEmpty()) {
            throw new PlayerDoesNotExistException("Player with id " + playerRequest.getPlayerId() + " does not exist.");
        }

        Player player2 = playerRequestMapper.mapPlayerRequestToPlayer(playerRequest);
        player2.getStats().setPlayer(player2);

        return playerResponseMapper.mapPlayerToPlayerResponse(playerRepository.save(player2));
    }

    public void deleteAllPlayers() {
        playerRepository.deleteAll();
    }
}
