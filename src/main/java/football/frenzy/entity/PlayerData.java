package football.frenzy.entity;

import jakarta.persistence.*;
import jakarta.persistence.Id;

import java.util.ArrayList;

@Entity
public class PlayerData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clubName;
    private String playerName;
    private String position;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private ClubData club;

    public PlayerData() {
        // Default constructor for JPA
    }

    public PlayerData(String clubName, ArrayList<String> playerName, String position, ClubData club) {
        this.clubName = clubName;
        this.playerName = String.valueOf(playerName);
        this.position = position;
        this.club = club;
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

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String toString() {
        return clubName + ": " + playerName;
    }
}
