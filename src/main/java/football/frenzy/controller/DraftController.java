package football.frenzy.controller;

import football.frenzy.dataaccess.DraftRepository;
import football.frenzy.entity.DraftData;
import football.frenzy.entity.PlayerData;
import football.frenzy.service.DraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/draft")
public class DraftController {

    @Autowired
    private DraftService draftService;

    @Autowired
    private DraftRepository draftRepository;

    @GetMapping("/start")
    @ResponseBody
    public DraftData startDraft() {
        DraftData draftData = draftService.initiateDraft();
        return draftData;
    }

    @PostMapping("/select")
    public ResponseEntity<String> selectPlayer(
            @RequestParam Long draftId,
            @RequestParam String userKey,
            @RequestParam String selectedPlayer
    ) {
        try {
            DraftData draftData = draftRepository.findById(draftId)
                    .orElseThrow(() -> new RuntimeException("Draft not found"));

            // Check if the user has already made selections
            List<String> userSelections = draftData.getSelectedPlayersByUser()
                    .computeIfAbsent(userKey, key -> new ArrayList<>());

            // Validate if the selected player is available
            if (draftData.getAvailablePlayers().contains(selectedPlayer) && !userSelections.contains(selectedPlayer)) {
                userSelections.add(selectedPlayer);
                draftData.getAvailablePlayers().remove(selectedPlayer);

                draftRepository.save(draftData);

                return ResponseEntity.ok("Player selected successfully");
            } else {
                // Handle invalid selection (e.g., player not available or already selected)
                return ResponseEntity.badRequest().body("Invalid player selection");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}
