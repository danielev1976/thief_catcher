package io.education.thief_catcher.repository;

import io.education.thief_catcher.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Integer> {
}
