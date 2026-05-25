package io.education.thief_catcher.service;

import io.education.thief_catcher.entity.GameMap;
import io.education.thief_catcher.repository.GameRepository;
import org.springframework.stereotype.Service;

@Service
public class MapService {
    private final GameRepository gameRepository;

    public MapService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public GameMap getMapById(Integer integer) {
    return new GameMap();
    }
}
