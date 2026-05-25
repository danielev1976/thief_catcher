package io.education.thief_catcher.dto.commence;

public record StartGameRequest(
        Integer gameId,
        Integer hostPlayerId
) {}