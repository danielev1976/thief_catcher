package io.education.thief_catcher.service;

import io.education.thief_catcher.dto.PlayerDto;
import io.education.thief_catcher.entity.Player;
import io.education.thief_catcher.enums.Role;
import io.education.thief_catcher.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import tools.jackson.databind.deser.bean.CreatorCandidate;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;


    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player getPlayerById(int id){
       return playerRepository.findById(id).orElseThrow();
    }
    public Player getPlayerByName(String playerName){
        return playerRepository.findByUsername(playerName);
    }

    public PlayerDto getPlayerDtoByName(String playerName){
        Player player = playerRepository.findByUsername(playerName);
        String username = player.getUsername();
        Role role = player.getRole();
        return new PlayerDto(username, role);
    }
}
