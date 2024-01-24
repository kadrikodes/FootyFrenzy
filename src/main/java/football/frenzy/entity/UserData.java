package football.frenzy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String userPassword;

    @OneToMany
    private List<PlayerData> selectedPlayers = new ArrayList<>();

    public UserData() {
        // Default constructor for JPA
    }

    public UserData(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public List<PlayerData> getSelectedPlayers() {
        return selectedPlayers;
    }

    public void addSelectedPlayer(PlayerData player) {
        selectedPlayers.add(player);
    }

    public static List<UserData> createUserFromCSV() {
        // Implement loading users from CSV if needed
        return new ArrayList<>();
    }
}
