package io.education.thief_catcher.dto;

import io.education.thief_catcher.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class PlayerDto {

    private String playerUsername;

    @Enumerated(EnumType.STRING)
    private Role playerRole;

    public PlayerDto(String playerUsername, Role playerRole) {
        this.playerUsername = playerUsername;
        this.playerRole = playerRole;
    }

    public PlayerDto(){}

    public String getPlayerUsername() {
        return playerUsername;
    }

    public void setPlayerUsername(String playerUsername) {
        this.playerUsername = playerUsername;
    }

    public Role getPlayerRole() {
        return playerRole;
    }

    public void setPlayerRole(Role playerRole) {
        this.playerRole = playerRole;
    }
}
