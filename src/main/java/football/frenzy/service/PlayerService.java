package football.frenzy.service;

import football.frenzy.dataaccess.ClubRepository;
import football.frenzy.dataaccess.PlayerRepository;
import football.frenzy.entity.ClubData;
import football.frenzy.entity.DraftData;
import football.frenzy.entity.PlayerData;
import football.frenzy.entity.model.DraftRound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final ClubRepository clubRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, ClubRepository clubRepository) {
        this.playerRepository = playerRepository;
        this.clubRepository = clubRepository;
    }

    public List<PlayerData> getAllPlayers() {
        return playerRepository.findAll();
    }

    public List<PlayerData> getPlayersByClubId(Long clubId) {
        return playerRepository.findByClubClubId(clubId);
    }
    public List<PlayerData> getPlayersByClub(String clubName) {
        return playerRepository.findByClubClubName(clubName);
    }
    public PlayerData getPlayerById(Long playerId) {
        Optional<PlayerData> playerOptional = playerRepository.findById(playerId);
        return playerOptional.orElse(null);
    }
    public List<PlayerData> getPlayersByPosition(String position) {
        return playerRepository.findByPosition(position);
    }

    public List<PlayerData> getPlayerDataByPlayerName(String name) {
        return playerRepository.findPlayerDataByPlayerName(name);
    }

//    public PlayerData addPlayer(PlayerData player) {
//        return playerRepository.save(player);
//    }

    @Transactional
    public PlayerData addPlayer(PlayerData playerData, Long clubId) {
        ClubData club = clubRepository.findById(clubId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid club ID: " + clubId));
        playerData.setClub(club);
        return playerRepository.save(playerData);
    }

    public PlayerData addPlayerToClub(Long clubId, PlayerData player) {
        ClubData club = clubRepository.findById(clubId).orElseThrow(() -> new RuntimeException("Club not found"));
        player.setClub(club);
        return playerRepository.save(player);
    }

    public boolean isValidSelectedPlayerSelection(DraftData draftData, String selectedPlayer) {
        // Get the current club and round from the draft
        ClubData currentClub = draftData.getCurrentClub();
        DraftRound currentRound = draftData.getRoundNumber();

        // Check if the selected player belongs to the current club and is available in the current round
        return currentClub.getPlayers().stream()
                .anyMatch(player -> player.getPlayerName().equals(selectedPlayer))
                && draftData.getAvailablePlayers().contains(selectedPlayer);

    }

    // TODO add more methods based on what functionalities I would want for the players.
}

