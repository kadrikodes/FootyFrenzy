package football.frenzy.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_draft_selection")
public class UserDraftSelection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long selectionId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData userId;

    @ManyToOne
    @JoinColumn(name = "draft_id")
    private DraftData draftId;

    private int roundNumber;

    @ManyToOne
    @JoinColumn(name = "selected_club_id")
    private ClubData selectedClub;

    public UserDraftSelection(UserData user, DraftData draft, int roundNumber, ClubData selectedClub) {
        this.userId = user;
        this.draftId = draft;
        this.roundNumber = roundNumber;
        this.selectedClub = selectedClub;
    }

    public Long getSelectionId() {
        return selectionId;
    }

    public void setSelectionId(Long selectionId) {
        this.selectionId = selectionId;
    }

    public UserData getUserId() {
        return userId;
    }

    public void setUserId(UserData userId) {
        this.userId = userId;
    }

    public DraftData getDraftId() {
        return draftId;
    }

    public void setDraftId(DraftData draftId) {
        this.draftId = draftId;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public ClubData getSelectedClub() {
        return selectedClub;
    }

    public void setSelectedClub(ClubData selectedClub) {
        this.selectedClub = selectedClub;
    }

    public Long getUserIdAsLong() {
        return userId != null ? userId.getUserId() : null;
    }

    public Long getDraftIdAsLong() {
        return (draftId != null) ? draftId.getDraftId() : null;
    }

    public void setUserIdFromLong(Long userId) {
        if (userId != null) {
            this.userId = new UserData(); // Assuming you have a no-arg constructor in UserData
            this.userId.setUserId(userId);
        } else {
            this.userId = null;
        }
    }

    public void setDraftIdFromLong(Long draftId) {
        if (draftId != null) {
            this.draftId = new DraftData(); // Assuming you have a no-arg constructor in DraftData
            this.draftId.setDraftId(draftId);
        } else {
            this.draftId = null;
        }
    }

}

