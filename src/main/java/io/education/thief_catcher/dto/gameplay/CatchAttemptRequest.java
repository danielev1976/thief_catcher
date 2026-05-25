package io.education.thief_catcher.dto.gameplay;

public record CatchAttemptRequest(
        Integer gameId,
        Integer detectivePlayerId,
        Integer thiefPlayerId,
        Integer locationId
) {}