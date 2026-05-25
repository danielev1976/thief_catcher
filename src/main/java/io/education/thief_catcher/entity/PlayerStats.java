package io.education.thief_catcher.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// PlayerStats.java
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "player_id", nullable = false, unique = true)
    private Player player;

    @Column(name = "games_played")
    private Integer gamesPlayed = 0;

    @Column(name = "games_won")
    private Integer gamesWon = 0;

    @Column(name = "times_caught")
    private Integer timesCaught = 0;

    @Column(name = "thieves_caught")
    private Integer thievesCaught = 0;
}