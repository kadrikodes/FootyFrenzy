package football.frenzy.entity;

import football.frenzy.entity.model.DraftRound;
import football.frenzy.entity.model.DraftStatus;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "draft")
public class DraftData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long draftId;

    @Enumerated(EnumType.STRING)
    private DraftRound roundNumber;

    @ManyToOne
    @JoinColumn(name = "current_club_id")
    private ClubData currentClub;

    @Enumerated(EnumType.STRING)
    private DraftStatus status;

    @ManyToMany
    @JoinTable(
            name = "draft_available_clubs",
            joinColumns = @JoinColumn(name = "draft_id"),
            inverseJoinColumns = @JoinColumn(name = "club_id")
    )
    private List<ClubData> availableClubs;

    public DraftData(DraftRound roundNumber, ClubData currentClub, DraftStatus status, List<ClubData> availableClubs) {
        this.roundNumber = roundNumber;
        this.currentClub = currentClub;
        this.status = status;
        this.availableClubs = availableClubs;
    }

    public Long getDraftId() {
        return draftId;
    }

    public void setDraftId(Long draftId) {
        this.draftId = draftId;
    }

    public DraftRound getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(DraftRound roundNumber) {
        this.roundNumber = roundNumber;
    }

    public ClubData getCurrentClub() {
        return currentClub;
    }

    public void setCurrentClub(ClubData currentClub) {
        this.currentClub = currentClub;
    }

    public DraftStatus getStatus() {
        return status;
    }

    public void setStatus(DraftStatus status) {
        this.status = status;
    }

    public List<ClubData> getAvailableClubs() {
        return availableClubs;
    }

    public void setAvailableClubs(List<ClubData> availableClubs) {
        this.availableClubs = availableClubs;
    }
// Other attributes, getters, setters, and relationships...

    // Constructors, methods, etc.
}
