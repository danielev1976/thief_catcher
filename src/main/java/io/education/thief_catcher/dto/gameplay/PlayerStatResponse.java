package io.education.thief_catcher.dto.gameplay;

public record PlayerStatResponse(
        Integer playerId,
        String username,
        Integer gamesPlayed,
        Integer gamesWon,
        Integer timesCaught,
        Integer thievesCaught
) {}
