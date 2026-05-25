package io.education.thief_catcher.dto.gameplay;

import io.education.thief_catcher.entity.Route;

import java.time.LocalDateTime;

public record MoveResponse(
        Integer id,
        Integer playerId,
        Integer fromLocationId,
        Integer toLocationId,
        Route.TransportType transport,
        Integer turnNumber,
        LocalDateTime movedAt
) {}
