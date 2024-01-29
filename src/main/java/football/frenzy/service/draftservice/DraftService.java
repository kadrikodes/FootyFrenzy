package football.frenzy.service.draftservice;

import football.frenzy.entity.DraftData;
import football.frenzy.entity.UserData;
import football.frenzy.entity.UserDraftSelection;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DraftService {
    DraftData initiateDraft();

    ResponseEntity<String> selectPlayer(Long draftId, String userName, String selectedPlayer);

    DraftData getDraftStatus(DraftData draftId);

    boolean isValidPlayerSelection(DraftData draftData, String selectedPlayer);

    void updateDraftState(DraftData draftData, String userName, String selectedPlayer);

    List<UserDraftSelection> getUserDraftSelections(UserData userId, DraftData draftId);
}
