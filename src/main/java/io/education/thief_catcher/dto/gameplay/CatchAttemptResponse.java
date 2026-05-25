package io.education.thief_catcher.dto.gameplay;

import io.education.thief_catcher.entity.Game;

import java.time.LocalDateTime;

public record CatchAttemptResponse(
        Integer id,
        Boolean successful,
        LocalDateTime attemptedAt,
        Game.WinnerRole winnerRole
) {}