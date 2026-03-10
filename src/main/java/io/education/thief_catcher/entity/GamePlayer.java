package io.education.thief_catcher.entity;

import io.education.thief_catcher.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GamePlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role; // detective or thief — assigned when joining a game

    @Column(name = "is_caught", nullable = false)
    private Boolean isCaught = false;

    @Column(name = "joined_at", updatable = false)
    private LocalDateTime joinedAt;
}
