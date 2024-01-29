package football.frenzy;

import football.frenzy.entity.ClubData;
import football.frenzy.entity.PlayerData;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TestUtilities {

    public ArrayList<ClubData> createClubData() {
        // Create Liverpool club and players
        ClubData liverpoolClub = new ClubData("Liverpool", new ArrayList<>());
        PlayerData liverpoolPlayer = new PlayerData("Liverpool", "Alisson Becker", "GoalKeeper", liverpoolClub);
        liverpoolClub.getPlayers().add(liverpoolPlayer);

        // Create Arsenal club and players
        ClubData arsenalClub = new ClubData("Arsenal", new ArrayList<>());
        PlayerData arsenalPlayer = new PlayerData("Arsenal", "Bukayo Saka", "Attacker", arsenalClub);
        arsenalClub.getPlayers().add(arsenalPlayer);

        //store list of clubs created
        ArrayList<ClubData> clubs = new ArrayList<>();
        clubs.add(liverpoolClub);
        clubs.add(arsenalClub);

        // Return the list of created clubs
        return clubs;
    }

    public ArrayList<PlayerData> createPlayerData() {
        // Create Liverpool club and players
        ClubData liverpoolClub = new ClubData("Liverpool", new ArrayList<>());
        PlayerData liverpoolPlayer = new PlayerData("Liverpool", "Alisson Becker", "GoalKeeper", liverpoolClub);
        liverpoolClub.getPlayers().add(liverpoolPlayer);

        // Create Arsenal club and players
        ClubData arsenalClub = new ClubData("Arsenal", new ArrayList<>());
        PlayerData arsenalPlayer = new PlayerData("Arsenal", "Bukayo Saka", "Attacker", arsenalClub);
        arsenalClub.getPlayers().add(arsenalPlayer);

        //store list of players created
        ArrayList<PlayerData> players = new ArrayList<>();
        players.add(liverpoolPlayer);
        players.add(arsenalPlayer);

        // Return the list of created players
        return players;
    }
}
