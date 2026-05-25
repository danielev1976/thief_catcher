package io.education.thief_catcher.dto.gameplay;

import io.education.thief_catcher.entity.Route;

public record MoveRequest(
        Integer gameId,
        Integer playerId,
        Integer fromLocationId,
        Integer toLocationId,
        Route.TransportType transportType
) {}