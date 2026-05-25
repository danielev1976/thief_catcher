package io.education.thief_catcher.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Game {

    public enum Status {
        WAITING, ACTIVE, COMPLETED, ABANDONED
    }

    public enum WinnerRole {
        DETECTIVE, THIEF
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('WAITING','ACTIVE','COMPLETED','ABANDONED') DEFAULT 'WAITING'")
    private Status status = Status.WAITING;

    @ManyToOne
    private GameMap gameMap;

    @Column(name = "started_at")
    private LocalDateTime startedAt;

    @Column(name = "ended_at")
    private LocalDateTime endedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "winner_role", columnDefinition = "ENUM('DETECTIVE','THIEF')")
    private WinnerRole winnerRole;

    @Column(name = "created_at", nullable = false, updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }


}
