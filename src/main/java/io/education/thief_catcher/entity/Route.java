package io.education.thief_catcher.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Route.java
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "map_id", nullable = false)
    private GameMap gameMap;

    @ManyToOne
    @JoinColumn(name = "from_location", nullable = false)
    private Location fromLocation;

    @ManyToOne
    @JoinColumn(name = "to_location", nullable = false)
    private Location toLocation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransportType transport = TransportType.foot;

    @Column(nullable = false)
    private Integer distance = 1;

    public enum TransportType {
        foot, vehicle, subway
    }
}