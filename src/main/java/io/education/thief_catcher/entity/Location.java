package io.education.thief_catcher.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// Location.java
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "map_id", nullable = false)
    private GameMap gameMap;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "coord_x", nullable = false)
    private Integer coordX;

    @Column(name = "coord_y", nullable = false)
    private Integer coordY;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LocationType type = LocationType.street;

    @OneToMany(mappedBy = "fromLocation", cascade = CascadeType.ALL)
    private List<Route> outgoingRoutes;

    @OneToMany(mappedBy = "toLocation", cascade = CascadeType.ALL)
    private List<Route> incomingRoutes;

    public enum LocationType {
        street, building, hideout, checkpoint
    }
}