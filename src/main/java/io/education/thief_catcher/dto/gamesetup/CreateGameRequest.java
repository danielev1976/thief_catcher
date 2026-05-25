package io.education.thief_catcher.dto.gamesetup;

public record CreateGameRequest(
        Integer mapId,
        Integer playerId
) {}
