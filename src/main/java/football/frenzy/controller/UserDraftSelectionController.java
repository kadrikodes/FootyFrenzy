package football.frenzy.controller;

import football.frenzy.entity.DraftData;
import football.frenzy.entity.UserData;
import football.frenzy.entity.UserDraftSelection;
import football.frenzy.service.UserDraftSelectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/{userId}/draft/{draftId}/selection")
public class UserDraftSelectionController {

    private final UserDraftSelectionService userDraftSelectionService;

    @Autowired
    public UserDraftSelectionController(UserDraftSelectionService userDraftSelectionService) {
        this.userDraftSelectionService = userDraftSelectionService;
    }

    @GetMapping
    public ResponseEntity<List<UserDraftSelection>> getUserSelections(
            @PathVariable Long userId,
            @PathVariable Long draftId
    ) {
        List<UserDraftSelection> userSelections = userDraftSelectionService.getUserSelections(userId, draftId);
        return ResponseEntity.ok(userSelections);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUserSelection(
            @PathVariable UserData userId,
            @PathVariable DraftData draftId,
            @RequestBody UserDraftSelection userDraftSelection
    ) {
        // Set the user ID and draft ID from the path variables
        userDraftSelection.setUserId(userId);
        userDraftSelection.setDraftId(draftId);

        ResponseEntity<String> response = userDraftSelectionService.addUserSelection(userDraftSelection);
        return response;
    }

    // Add other endpoints as needed


}


