package football.frenzy.entity;

import jakarta.persistence.*;
import jakarta.persistence.Id;


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
    private ClubData club;

    @ManyToOne
    @JoinColumn(name = "draft_id")
    private DraftData draftData;

    public PlayerData(String clubName, String playerName, String position, ClubData club) {
        this.clubName = clubName;
        this.playerName = playerName;
        this.position = position;
        this.club = club;
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

    public ClubData getClub() {
        return club;
    }

    public void setClub(ClubData club) {
        this.club = club;
    }

    public String toString() {
        return clubName + ": " + playerName;
    }
}
