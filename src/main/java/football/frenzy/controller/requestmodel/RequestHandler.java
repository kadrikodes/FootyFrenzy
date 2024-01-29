package football.frenzy.controller.requestmodel;

import football.frenzy.entity.DraftData;

public class RequestHandler {
    private DraftData draftId;
    private String selectedPlayer;

    public DraftData getDraftId() {
        return draftId;
    }

    public void setDraftId(DraftData draftId) {
        this.draftId = draftId;
    }

    public String getSelectedPlayer() {
        return selectedPlayer;
    }

    public void setSelectedPlayer(String selectedPlayer) {
        this.selectedPlayer = selectedPlayer;
    }
}
