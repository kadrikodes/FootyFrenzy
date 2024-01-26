package football.frenzy.service;

import football.frenzy.dataaccess.UserDraftSelectionRepository;
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

    public List<UserDraftSelection> getUserSelections(Long userId, Long draftId) {
        // Implement logic to retrieve user selections for a specific draft
        // You can use the repository to query the database
        // For example:
        return userDraftSelectionRepository.findByUserIdAndDraftId(userId, draftId);
    }

    public ResponseEntity<String> addUserSelection(UserDraftSelection userDraftSelection) {
        // Implement logic to validate and add user draft selection
        // You can perform validation checks and save the selection to the database
        // Return an appropriate response

        // Example:
        userDraftSelectionRepository.save(userDraftSelection);
        return ResponseEntity.ok("User selection added successfully");
    }

    // Add other methods as needed

}

