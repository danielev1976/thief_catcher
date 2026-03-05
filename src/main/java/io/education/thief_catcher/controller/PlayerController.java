package io.education.thief_catcher.controller;

import io.education.thief_catcher.dto.PlayerDto;
import io.education.thief_catcher.entity.Player;
import io.education.thief_catcher.service.PlayerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/entity/{name}")
    public Player getPlayerByName(@PathVariable String name){
        return playerService.getPlayerByName(name);
    }

    @GetMapping("/{name}")
    public PlayerDto getPlayerDtoByName(@PathVariable String name){
        return playerService.getPlayerDtoByName(name);
    }
}
