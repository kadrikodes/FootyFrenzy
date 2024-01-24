package football.frenzy.controller;

import football.frenzy.entity.ClubData;
import football.frenzy.entity.PlayerData;
import football.frenzy.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
public class ClubController {
    private final ClubService clubService;

    @Autowired
    public ClubController(ClubService clubDataService) {
        this.clubService = clubDataService;
    }

    @GetMapping("/clubs")
    public String getAllClubs(Model model) {
        List<ClubData> allClubs = clubService.getAllClubs();
        model.addAttribute("clubs", allClubs);
        return "clubList"; // Thymeleaf template name
    }

    @GetMapping("/clubData")
    public List<ClubData> getAllClubData() {
        return clubService.findAll();
    }

    @GetMapping("/{clubId}")
    public ClubData getClubDataById(@PathVariable long clubId) {
        ClubData clubData = clubService.getClubDataById(clubId);

        if (clubData == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Club data not found");

        return clubData;
    }

    @GetMapping("/name/{name}")
    public List<ClubData> getClubDataByName(@PathVariable String name) {
        return clubService.getClubDataByClubName(name);
    }

    @PostMapping("/addClub")
    public String addClub(ClubData clubData) {
        clubService.addClub(clubData);
        return "redirect:/clubs"; // Redirect to the list of players after adding a new one
    }

//    @GetMapping("/search")
//    public List<ClubData> searchClubData(@RequestParam(required = false) String position) {
//        return playerService.searchEntriesByCriteria(position);
//    }

    // Additional methods for updating, deleting, or other club-related operations
}
