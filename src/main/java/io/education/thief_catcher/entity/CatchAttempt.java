package io.education.thief_catcher.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// CatchAttempt.java
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatchAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "detective_id", nullable = false)
    private Player detective;

    @ManyToOne
    @JoinColumn(name = "thief_id", nullable = false)
    private Player thief;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Column(nullable = false)
    private Boolean successful;

    @Column(name = "attempted_at", updatable = false)
    private LocalDateTime attemptedAt;
}
