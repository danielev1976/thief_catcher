package io.education.thief_catcher.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// Move.java
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Move {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "from_loc")
    private Location fromLocation;

    @ManyToOne
    @JoinColumn(name = "to_loc", nullable = false)
    private Location toLocation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransportType transport = TransportType.foot;

    @Column(name = "turn_number", nullable = false)
    private Integer turnNumber;

    @Column(name = "moved_at", updatable = false)
    private LocalDateTime movedAt;

    public enum TransportType {
        foot, vehicle, subway
    }
}
