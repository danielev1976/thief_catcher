package io.education.thief_catcher.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Map.java
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "grid_width", nullable = false)
    private Integer gridWidth;

    @Column(name = "grid_height", nullable = false)
    private Integer gridHeight;

    @Column(columnDefinition = "TEXT")
    private String description;



/*
    @OneToMany(mappedBy = "gameMap", cascade = CascadeType.ALL)
    private List<Location> locations;

    @OneToMany(mappedBy = "gameMap", cascade = CascadeType.ALL)
    private List<Route> routes;

    @OneToMany(mappedBy = "gameMap", cascade = CascadeType.ALL)
    private List<Game> games;


 */
}

