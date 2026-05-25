package io.education.thief_catcher.repository;

import io.education.thief_catcher.entity.GameMap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameMapRepository extends JpaRepository<GameMap, Integer> {
}
