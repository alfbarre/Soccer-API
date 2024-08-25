package com.soccerapi.soccerapi.controllers;

import com.soccerapi.soccerapi.models.playerrequestv1.PlayerRequest;
import com.soccerapi.soccerapi.models.playerresponsev1.PlayerResponse;
import com.soccerapi.soccerapi.services.PlayerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping(value = "/api/v1/players")
    @ResponseStatus(HttpStatus.OK)
    public List<PlayerResponse> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @PostMapping(value = "/api/v1/player")
    @ResponseStatus(HttpStatus.CREATED)
    public PlayerResponse addPlayers(@Valid @RequestBody PlayerRequest playerRequest) {
        return playerService.addPlayer(playerRequest);
    }

    @DeleteMapping(value = "/api/v1/player/{playerId}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePlayer(@PathVariable("playerId") UUID playerId) {
        playerService.deletePlayer(playerId);
    }

    @GetMapping(value = "/api/v1/player")
    @ResponseStatus(HttpStatus.OK)
    public PlayerResponse getPlayer(@RequestParam(name = "playerId") UUID playerId) {
        return playerService.findPlayerById(playerId);
    }

    @PutMapping(value = "/api/v1/player")
    @ResponseStatus(HttpStatus.OK)
    public PlayerResponse updatePlayer(@RequestBody PlayerRequest playerRequest) {
        return playerService.updatePlayer(playerRequest);
    }

    @DeleteMapping(value = "/api/v1/players")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllPlayers() {
        playerService.deleteAllPlayers();
    }


}
