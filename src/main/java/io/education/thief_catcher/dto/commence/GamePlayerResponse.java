package io.education.thief_catcher.dto.commence;


import io.education.thief_catcher.entity.GamePlayer;

import java.time.LocalDateTime;

public record GamePlayerResponse(
        Integer id,
        Integer playerId,
        String username,
        GamePlayer.GameRole gameRole,
        Boolean isCaught,
        LocalDateTime joinedAt,
        Integer currentLocationId
) {}