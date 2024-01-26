package football.frenzy.dataaccess;

import football.frenzy.entity.ClubData;
import football.frenzy.entity.PlayerData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<PlayerData, Long> {
    List<PlayerData> findPlayerDataByPlayerNames(String name);

    List<PlayerData> findByPosition(String position);

    List<PlayerData> findByClubName(ClubData randomClub);

    List<PlayerData> findByClub(ClubData randomClub);
}
