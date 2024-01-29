package football.frenzy.controller;

import football.frenzy.entity.DraftData;
import football.frenzy.service.draftservice.DraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/draft")
public class DraftController {

    private final DraftService draftService;

    @Autowired
    public DraftController(DraftService draftService) {
        this.draftService = draftService;
    }

    @PostMapping("/start")
    public ResponseEntity<DraftData> startDraft() {
        DraftData draftData = draftService.initiateDraft();
        return ResponseEntity.ok(draftData);
    }

    @PostMapping("/select")
    public ResponseEntity<String> selectPlayer(
            @RequestParam Long draftId,
            @RequestParam String userName,
            @RequestParam String selectedPlayer
    ) {
        try {
            ResponseEntity<String> draftData = draftService.selectPlayer(draftId, userName, selectedPlayer);
            return ResponseEntity.ok("Player selected successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid player selection");
        }
    }
}
