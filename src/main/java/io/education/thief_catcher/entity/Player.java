package io.education.thief_catcher.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, unique = true, length = 100)
    private String email;


    private enum Role {THIEF, DETECTIVE, NPC}; // default to player

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @OneToOne(mappedBy = "player", cascade = CascadeType.ALL)
    private PlayerStats playerStats;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<GamePlayer> gamePlayers;
}