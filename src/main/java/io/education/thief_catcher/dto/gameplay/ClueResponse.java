package io.education.thief_catcher.dto.gameplay;

import java.time.LocalDateTime;

public record ClueResponse(
        Integer id,
        Integer gameId,
        Integer locationId,
        String description,
        Boolean isFound,
        LocalDateTime foundAt
) {}
