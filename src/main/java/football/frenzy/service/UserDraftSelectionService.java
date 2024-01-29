package football.frenzy.service;

import football.frenzy.dataaccess.UserDraftSelectionRepository;
import football.frenzy.entity.DraftData;
import football.frenzy.entity.UserData;
import football.frenzy.entity.UserDraftSelection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDraftSelectionService {

    private final UserDraftSelectionRepository userDraftSelectionRepository;

    @Autowired
    public UserDraftSelectionService(UserDraftSelectionRepository userDraftSelectionRepository) {
        this.userDraftSelectionRepository = userDraftSelectionRepository;
    }

    public List<UserDraftSelection> getUserSelections(UserData userId, DraftData draftId) {
        return userDraftSelectionRepository.findByUserIdAndDraftId(userId, draftId);
    }

    public ResponseEntity<String> addUserSelection(UserDraftSelection userDraftSelection) {
        // TODO Implement more logic to validate and add user draft selection
        userDraftSelectionRepository.save(userDraftSelection);
        return ResponseEntity.ok("User selection added successfully");
    }

    // TODO Add other methods as needed

}

