package io.education.thief_catcher.service;

import io.education.thief_catcher.entity.GameMap;
import io.education.thief_catcher.repository.GameMapRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameMapService {
    private final GameMapRepository gameMapRepository;
    public GameMap getGameMapById(Integer id) {
       return gameMapRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "Map not found with id: " + id));
    }
}
