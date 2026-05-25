package io.education.thief_catcher.dto.commence;


import io.education.thief_catcher.entity.GamePlayer;

public record AssignRoleRequest(
        Integer gameId,
        Integer playerId,
        GamePlayer.GameRole gameRole
) {}