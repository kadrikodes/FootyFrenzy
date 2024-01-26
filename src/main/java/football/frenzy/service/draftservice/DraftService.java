package football.frenzy.service.draftservice;

import football.frenzy.entity.DraftData;
import football.frenzy.entity.UserDraftSelection;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DraftService {
    DraftData initiateDraft();

    ResponseEntity<String> selectPlayer(Long draftId, String userKey, String selectedPlayer);

    DraftData getDraftStatus(Long draftId);

    boolean isValidPlayerSelection(DraftData draftData, String selectedPlayer);

    void updateDraftState(DraftData draftData, String userKey, String selectedPlayer);

    List<UserDraftSelection> getUserDraftSelections(Long userId, Long draftId);
}
