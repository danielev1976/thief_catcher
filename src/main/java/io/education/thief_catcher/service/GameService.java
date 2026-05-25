package io.education.thief_catcher.service;

import io.education.thief_catcher.dto.gamesetup.CreateGameRequest;
import io.education.thief_catcher.entity.Game;
import io.education.thief_catcher.entity.GameMap;
import io.education.thief_catcher.entity.GamePlayer;
import io.education.thief_catcher.entity.Player;
import io.education.thief_catcher.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final GameMapService gameMapService;
    private final PlayerService playerService;
    private final GamePlayerService gamePlayerService;


    @Transactional
    public Game createGame(CreateGameRequest request) {

       // int id = request.mapId();

        int id = 1;
        // 1. Verify the map exists
        GameMap gameMap = gameMapService.getGameMapById(id);


        // 2. Verify the host player exists
        Player host = playerService.getPlayerById(request.playerId());


        // 3. Create and save the game
        Game game = new Game();
        game.setGameMap(gameMap);
        game.setStatus(Game.Status.WAITING);
        game.setCreatedAt(LocalDateTime.now());
        Game savedGame = gameRepository.save(game);

        // 4. Add the host as the first game player without a role yet
        GamePlayer hostPlayer = new GamePlayer();
        hostPlayer.setGame(savedGame);
        hostPlayer.setPlayer(host);
        hostPlayer.setIsCaught(false);
        hostPlayer.setJoinedAt(LocalDateTime.now());
        gamePlayerService.addPlayer(hostPlayer);

        // 5. Map to response and return
        return savedGame;
    }


}
