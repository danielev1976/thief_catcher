package io.education.thief_catcher.dto.gameplay;

import io.education.thief_catcher.dto.commence.GamePlayerResponse;
import io.education.thief_catcher.entity.Game;

import java.util.List;

public record GameStateResponse(
        Integer gameId,
        Integer currentTurn,
        Game.Status status,
        GameMapResponse map,
        List<GamePlayerResponse> players,
        List<ClueResponse> visibleClues
) {}