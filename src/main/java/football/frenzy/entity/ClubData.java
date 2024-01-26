package football.frenzy.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;



import java.util.List;

@Entity
public class ClubData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clubId;
    private String clubName;
    @OneToMany(mappedBy = "clubData", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlayerData> players;

    public ClubData() {
    }

    public ClubData(String clubName, List<PlayerData> players) {
        this.clubName = clubName;
        this.players = players;
    }

    public Long getClubId() {
        return clubId;
    }

    public void setClubId(Long clubId) {
        this.clubId = clubId;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public List<PlayerData> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerData> players) {
        this.players = players;
    }


}
