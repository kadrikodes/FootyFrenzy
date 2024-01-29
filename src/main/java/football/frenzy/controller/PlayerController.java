package football.frenzy.controller;

import football.frenzy.controller.requestmodel.RequestHandler;
import football.frenzy.entity.DraftData;
import football.frenzy.entity.PlayerData;
import football.frenzy.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public ResponseEntity<List<PlayerData>> getAllPlayers() {
        List<PlayerData> players = playerService.getAllPlayers();
        return ResponseEntity.ok(players);
    }

    @GetMapping("/{clubId}/players")
    public ResponseEntity<List<PlayerData>> getPlayersByClubId(@PathVariable Long clubId) {
        List<PlayerData> players = playerService.getPlayersByClubId(clubId);
        return ResponseEntity.ok(players);
    }

    @GetMapping("/byClub/{clubName}")
    public ResponseEntity<List<PlayerData>> getPlayersByClub(@PathVariable String clubName) {
        List<PlayerData> players = playerService.getPlayersByClub(clubName);

        if (!players.isEmpty()) {
            return ResponseEntity.ok(players);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/byPosition/{position}")
    public ResponseEntity<List<PlayerData>> getPlayersByPosition(@PathVariable String position) {
        List<PlayerData> players = playerService.getPlayersByPosition(position);

        if (!players.isEmpty()) {
            return ResponseEntity.ok(players);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<PlayerData> getPlayerById(@PathVariable Long playerId) {
        PlayerData player = playerService.getPlayerById(playerId);

        if (player != null) {
            return ResponseEntity.ok(player);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/byPlayerName/{name}")
    public ResponseEntity<List<PlayerData>> getPlayersByPlayerName(@PathVariable String name) {
        List<PlayerData> players = playerService.getPlayerDataByPlayerName(name);

        if (!players.isEmpty()) {
            return ResponseEntity.ok(players);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PlayerData> addPlayer(@RequestBody PlayerData player) {
        PlayerData addedPlayer = playerService.addPlayer(player);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedPlayer);
    }

    @PostMapping("/validate-selection")
    public ResponseEntity<String> validatePlayerSelection(
            @RequestBody RequestHandler selectionRequest) {
        DraftData draftId = selectionRequest.getDraftId();
        String selectedPlayer = selectionRequest.getSelectedPlayer();

        boolean isValidSelection = playerService.isValidSelectedPlayerSelection(draftId, selectedPlayer);

        if (isValidSelection) {
            return ResponseEntity.ok("Selected player is valid for the current draft round and club.");
        } else {
            return ResponseEntity.badRequest().body("Invalid player selection for the current draft round and club.");
        }
    }
}

