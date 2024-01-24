package football.frenzy.controller;

import football.frenzy.entity.PlayerData;
import football.frenzy.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/players")
    public String getAllPlayers(Model model) {
        List<PlayerData> allPlayers = playerService.getAllPlayers();
        model.addAttribute("players", allPlayers);
        return "playerList"; // Thymeleaf template name
    }

    @GetMapping("/playerData")
    public List<PlayerData> getAllPlayerData() {
        return playerService.findAll();
    }

    @GetMapping("/{playerId}")
    public PlayerData getPlayerDataById(@PathVariable long playerId) {
        PlayerData playerData = playerService.getPlayerDataById(playerId);

        if (playerData == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player data not found");

        return playerData;
    }

    @GetMapping("/name/{name}")
    public List<PlayerData> getPlayerDataByName(@PathVariable String name) {
        return playerService.getPlayerDataByPlayerName(name);
    }

    @PostMapping("/addPlayer")
    public String addPlayer(PlayerData player) {
        playerService.addPlayer(player);
        return "redirect:/players"; // Redirect to the list of players after adding a new one
    }

    @GetMapping("/search")
    public List<PlayerData> searchPlayerData(@RequestParam(required = false) String position) {
        return playerService.searchEntriesByCriteria(position);
    }

    // Additional methods for updating, deleting, or other player-related operations
}
