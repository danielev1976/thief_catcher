package io.education.thief_catcher.repository;

import io.education.thief_catcher.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
    Player findByUsername(String name);
}
