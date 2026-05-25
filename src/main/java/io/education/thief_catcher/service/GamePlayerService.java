package io.education.thief_catcher.service;

import io.education.thief_catcher.entity.GamePlayer;
import io.education.thief_catcher.repository.GamePlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GamePlayerService {

    private final GamePlayerRepository gamePlayerRepository;
    public void addPlayer(GamePlayer player) {
        gamePlayerRepository.save(player);

    }
}
