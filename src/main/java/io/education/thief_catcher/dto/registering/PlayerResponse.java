package io.education.thief_catcher.dto.registering;

import java.time.LocalDateTime;

public record PlayerResponse(Integer id, String username, String email, LocalDateTime createAt, LocalDateTime lastLogin) {
}
