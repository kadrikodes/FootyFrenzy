package football.frenzy.dataaccess;

import football.frenzy.entity.PlayerData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerData, Long> {

    List<PlayerData> findByClubName(String clubName);

    List<PlayerData> findByPosition(String position);

    List<PlayerData> findByClubClubId(Long clubId);

    List<PlayerData> findPlayerDataByPlayerName(String name);
}
