package football.frenzy.entity;

import jakarta.persistence.*;
import jakarta.persistence.Id;

import java.util.ArrayList;

@Entity
public class PlayerData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playerId;
    private String clubName;
    private String playerName;
    private String position;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private ClubData clubData;

    public PlayerData(String clubName, String playerName, String position, ClubData clubData) {
        this.clubName = clubName;
        this.playerName = playerName;
        this.position = position;
        this.clubData = clubData;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
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

    public ClubData getClubData() {
        return clubData;
    }

    public void setClubData(ClubData clubData) {
        this.clubData = clubData;
    }

    public String toString() {
        return clubName + ": " + playerName;
    }
}
