package football.frenzy.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;
import java.util.Map;

@Entity
public class DraftData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String randomClub;

    @ElementCollection
    private List<String> availablePlayers;

    @ElementCollection
    private Map<String, List<String>> selectedPlayersByUser;

    @ElementCollection
    private List<String> selectedClubs;

    private ClubData club;

    public ClubData getClub() {
        return club;
    }

    public void setClub(ClubData club) {
        this.club = club;
    }

    // Default constructor for Spring Data JPA
    public DraftData() {
    }

    public DraftData(String randomClub, List<String> availablePlayers, Map<String, List<String>> selectedPlayersByUser, List<String> selectedClubs) {
        this.randomClub = randomClub;
        this.availablePlayers = availablePlayers;
        this.selectedPlayersByUser = selectedPlayersByUser;
        this.selectedClubs = selectedClubs;
    }

    public Long getId() {
        return id;
    }

    public String getRandomClub() {
        return randomClub;
    }

    public List<String> getAvailablePlayers() {
        return availablePlayers;
    }

    public void setAvailablePlayers(List<String> availablePlayers) {
        this.availablePlayers = availablePlayers;
    }

    public Map<String, List<String>> getSelectedPlayersByUser() {
        return selectedPlayersByUser;
    }

    public List<String> getSelectedClubs() {
        return selectedClubs;
    }

    public void setAvailableClubs(List<String> availableClubs) {
        this.selectedClubs = availableClubs;
    }

    // Add setter methods for other fields as needed...

}

