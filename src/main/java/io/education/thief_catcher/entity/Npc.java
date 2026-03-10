package io.education.thief_catcher.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Npc {

    @Id
    private Integer id;

    @OneToOne
    @JoinColumn(name = "game_player_id", nullable = false)
    private GamePlayer gamePlayer;

    @Enumerated(EnumType.STRING)
    @Column(name = "npc_type", nullable = false)
    private Type type;

    @Enumerated(EnumType.STRING)
    @Column(name = "behavior", nullable = false)
    private Behavior behavior;

    @Column(name = "patrol_route", columnDefinition = "JSON")
    private String patrolRoute; // JSON array of location IDs

    @Column(name = "trigger_event")
    private String triggerEvent;

    public enum Type {
        PATROL_GUARD, WITNESS, INFORMANT, DECOY, CORRUPT_OFFICER
    }

    public enum Behavior {
        STATIONARY, PATROL, TRIGGERED
    }
}