package io.education.thief_catcher.entity;

import io.education.thief_catcher.enums.WINNER_ROLE;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class Game {

    @Enumerated(EnumType.STRING)
    WINNER_ROLE role;


}
