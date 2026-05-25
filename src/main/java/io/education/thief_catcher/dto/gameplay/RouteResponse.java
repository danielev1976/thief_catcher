package io.education.thief_catcher.dto.gameplay;

import io.education.thief_catcher.entity.Route;

public record RouteResponse(
        Integer id,
        Integer fromLocationId,
        Integer toLocationId,
        Route.TransportType transport,
        Integer distance
) {}