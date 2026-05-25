package io.education.thief_catcher.dto.gameplay;

import io.education.thief_catcher.entity.Location;

public record LocationResponse(
        Integer id,
        String name,
        Integer coordX,
        Integer coordY,
        Location.LocationType type
) {}