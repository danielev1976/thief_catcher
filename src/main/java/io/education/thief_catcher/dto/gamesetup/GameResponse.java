package io.education.thief_catcher.dto.gamesetup;


import io.education.thief_catcher.entity.Game;
import io.education.thief_catcher.entity.GamePlayer;

import java.time.LocalDateTime;

public record GameResponse(Integer id,
                           Game.Status status,
                           Integer mapId,
                           LocalDateTime startedAt,
                           LocalDateTime endedAt,
                           GamePlayer.GameRole winnerRole
                           ) { }
