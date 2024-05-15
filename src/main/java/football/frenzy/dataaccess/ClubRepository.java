package football.frenzy.dataaccess;

import football.frenzy.entity.ClubData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<ClubData, Long> {
    List<ClubData> findClubDataByClubName(String name);
    Optional<ClubData> findByClubName(String clubName);
}
