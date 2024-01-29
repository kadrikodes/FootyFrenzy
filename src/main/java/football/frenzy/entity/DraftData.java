package football.frenzy.entity;

import football.frenzy.entity.model.DraftRound;
import football.frenzy.entity.model.DraftStatus;
import jakarta.persistence.*;

import java.util.List;
import java.util.Map;


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

    @OneToMany(mappedBy = "draftData", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlayerData> availablePlayers;

    @ElementCollection
    @CollectionTable(name = "draft_selected_players", joinColumns = @JoinColumn(name = "draft_id"))
    @MapKeyColumn(name = "user_key")
    @Column(name = "selected_players")
    private Map<String, List<String>> selectedPlayersByUser;

    public DraftData() {
    }

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

    public List<PlayerData> getAvailablePlayers() {
        return availablePlayers;
    }

    public void setAvailablePlayers(List<PlayerData> availablePlayers) {
        this.availablePlayers = availablePlayers;
    }

    public Map<String, List<String>> getSelectedPlayersByUser() {
        return selectedPlayersByUser;
    }

    public void setSelectedPlayersByUser(Map<String, List<String>> selectedPlayersByUser) {
        this.selectedPlayersByUser = selectedPlayersByUser;
    }
}
