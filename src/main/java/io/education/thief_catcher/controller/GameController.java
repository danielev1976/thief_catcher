package io.education.thief_catcher.controller;

import io.education.thief_catcher.dto.gamesetup.CreateGameRequest;
import io.education.thief_catcher.entity.Game;
import io.education.thief_catcher.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    // =====================
    // 1. CREATE GAME
    // POST /games
    // Host creates a new game session and chooses a map
    // Returns a GameResponse with status = WAITING
    // =====================
    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody CreateGameRequest request) {
        Game response = gameService.createGame(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // =====================
    // 2. JOIN GAME
    // POST /games/{gameId}/join
    // A player joins an existing game session
    // Returns updated GameResponse with the new player in the players list
    // No role is assigned yet at this stage
    // =====================
/*
    @PostMapping("/{gameId}/join")
    public ResponseEntity<GameResponse> joinGame(
            @PathVariable Integer gameId,
            @RequestBody JoinGameRequest request) {
        GameResponse response = gameService.joinGame(gameId, request);
        return ResponseEntity.ok(response);
    }
*/

    // =====================
    // 3. ADD NPC
    // POST /games/{gameId}/npcs
    // Host adds an NPC to the game session
    // Returns NpcResponse with npc type, behavior and patrol route
    // =====================
/*    @PostMapping("/{gameId}/npcs")
    public ResponseEntity<NpcResponse> addNpc(
            @PathVariable Integer gameId,
            @RequestBody AddNpcRequest request) {
        NpcResponse response = gameService.addNpc(gameId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }*/

    // =====================
    // 4. ASSIGN ROLE
    // PATCH /games/{gameId}/players/{playerId}/role
    // Host assigns a role to a specific player before the game starts
    // Returns updated GamePlayerResponse with the assigned role
    // =====================
/*    @PatchMapping("/{gameId}/players/{playerId}/role")
    public ResponseEntity<GamePlayerResponse> assignRole(
            @PathVariable Integer gameId,
            @PathVariable Integer playerId,
            @RequestBody AssignRoleRequest request) {
        GamePlayerResponse response = gameService.assignRole(gameId, playerId, request);
        return ResponseEntity.ok(response);
    }*/

    // =====================
    // 5. START GAME
    // POST /games/{gameId}/start
    // Host starts the game once all players have roles
    // Returns GameResponse with status = ACTIVE
    // =====================
/*    @PostMapping("/{gameId}/start")
    public ResponseEntity<GameResponse> startGame(
            @PathVariable Integer gameId,
            @RequestBody StartGameRequest request) {
        GameResponse response = gameService.startGame(gameId, request);
        return ResponseEntity.ok(response);
    }*/

    // =====================
    // 6. GET GAME STATE
    // GET /games/{gameId}/state
    // Fetched by the client each turn to get the full picture of the game
    // Returns GameStateResponse with players, map, clues and current turn
    // =====================
/*
    @GetMapping("/{gameId}/state")
    public ResponseEntity<GameStateResponse> getGameState(@PathVariable Integer gameId) {
        GameStateResponse response = gameService.getGameState(gameId);
        return ResponseEntity.ok(response);
    }
*/

    // =====================
    // 7. GET GAME
    // GET /games/{gameId}
    // Returns a lightweight GameResponse summary
    // =====================
/*
    @GetMapping("/{gameId}")
    public ResponseEntity<GameResponse> getGame(@PathVariable Integer gameId) {
        GameResponse response = gameService.getGame(gameId);
        return ResponseEntity.ok(response);
    }
*/

    // =====================
    // 8. GET ALL ACTIVE GAMES
    // GET /games?status=ACTIVE
    // Returns a list of all games filtered by status
    // =====================
/*    @GetMapping
    public ResponseEntity<List<GameResponse>> getGames(
            @RequestParam(required = false)Game.Status status) {
        List<GameResponse> response = gameService.getGames(status);
        return ResponseEntity.ok(response);
    }*/
}


