package io.education.thief_catcher.dto.gamesetup;

import io.education.thief_catcher.entity.Npc;

import java.util.List;

public record NpcResponse(
        Integer id,
        Integer gamePlayerId,
        Npc.Type npcType,
        Npc.Behavior behavior,
        List<Integer> patrolRoute,
        String triggerEvent,
        Integer currentLocationId
) {}