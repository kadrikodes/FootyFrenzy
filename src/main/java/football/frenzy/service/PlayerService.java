package football.frenzy.service;

import football.frenzy.dataaccess.PlayerRepository;
import football.frenzy.entity.ClubData;
import football.frenzy.entity.DraftData;
import football.frenzy.entity.PlayerData;
import football.frenzy.entity.model.DraftRound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<PlayerData> getAllPlayers() {
        return playerRepository.findAll();
    }

    public List<PlayerData> getPlayersByClubId(Long clubId) {
        return playerRepository.findByClubClubId(clubId);
    }
    public List<PlayerData> getPlayersByClub(String clubName) {
        return playerRepository.findByClubName(clubName);
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

    public PlayerData addPlayer(PlayerData player) {
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

