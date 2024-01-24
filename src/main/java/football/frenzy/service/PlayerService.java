package football.frenzy.service;

import football.frenzy.dataaccess.PlayerRepository;
import football.frenzy.entity.PlayerData;
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

    public PlayerData addPlayer(PlayerData player) {
        return playerRepository.save(player);
    }

    public List<PlayerData> findAll() {
        return playerRepository.findAll();
    }

    public PlayerData getPlayerDataById(long playerId) {
        Optional<PlayerData> players = playerRepository.findById(playerId);
        return players.orElse(null);
    }

    public List<PlayerData> getPlayerDataByPlayerName(String name) {
        return playerRepository.findPlayerDataByPlayerNames(name);
    }

    public List<PlayerData> searchEntriesByCriteria(String position) {
        return playerRepository.findByPosition(position);
    }
}
