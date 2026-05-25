package io.education.thief_catcher.dto.gameplay;

public record FindClueRequest(
        Integer gameId,
        Integer playerId,
        Integer locationId
) {}