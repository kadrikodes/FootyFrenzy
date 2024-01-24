package football.frenzy.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;



import java.util.List;

@Entity
public class ClubData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clubName;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlayerData> players;

//    @ElementCollection
//    private List<String> players;

    // Default constructor for JPA
    public ClubData() {
    }

    public ClubData(String clubName, List<PlayerData> playerNameList) {
        this.clubName = clubName;
        this.players = playerNameList;
    }

    public Long getId() {
        return id;
    }

    public String getClubName() {
        return clubName;
    }
    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public void setPlayers(List<PlayerData> players) {
        this.players = players;
    }

    public List<PlayerData> getPlayers() {
        return players;
    }

}
