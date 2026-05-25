package io.education.thief_catcher.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// Clue.java
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Clue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clue_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @ManyToOne
    @JoinColumn(name = "found_by")
    private Player foundBy;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "is_found", nullable = false)
    private Boolean isFound = false;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "found_at")
    private LocalDateTime foundAt;
}
