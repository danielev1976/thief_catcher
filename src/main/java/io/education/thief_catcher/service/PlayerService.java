package io.education.thief_catcher.service;

import io.education.thief_catcher.entity.Player;
import io.education.thief_catcher.repository.PlayerRepository;
import org.springframework.stereotype.Service;

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
}
