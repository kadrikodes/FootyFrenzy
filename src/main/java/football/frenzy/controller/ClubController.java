package football.frenzy.controller;

import football.frenzy.entity.ClubData;
import football.frenzy.entity.PlayerData;
import football.frenzy.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clubs")
public class ClubController {

    private final ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping
    public ResponseEntity<List<ClubData>> getAllClubs() {
        List<ClubData> clubs = clubService.getAllClubs();
        return ResponseEntity.ok(clubs);
    }

    @GetMapping("/{clubId}")
    public ResponseEntity<ClubData> getClubById(@PathVariable Long clubId) {
        ClubData club = clubService.getClubById(clubId);
        if (club != null) {
            return ResponseEntity.ok(club);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{clubId}/players")
    public ResponseEntity<List<PlayerData>> getPlayersByClubId(@PathVariable Long clubId) {
        List<PlayerData> players = clubService.getPlayersByClubId(clubId);
        if (players != null && !players.isEmpty()) {
            return ResponseEntity.ok(players);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

