package io.education.thief_catcher.repository;

import io.education.thief_catcher.entity.GamePlayer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GamePlayerRepository extends JpaRepository<GamePlayer, Integer> {
}
