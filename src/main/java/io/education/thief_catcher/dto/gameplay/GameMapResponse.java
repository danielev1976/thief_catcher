package io.education.thief_catcher.dto.gameplay;

import java.util.List;

public record GameMapResponse(
        Integer id,
        String name,
        Integer gridWidth,
        Integer gridHeight,
        String description,
        List<LocationResponse> locations,
        List<RouteResponse> routes
) {}