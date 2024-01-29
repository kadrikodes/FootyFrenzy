package football.frenzy.dataaccess;

import football.frenzy.entity.DraftData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DraftRepository extends JpaRepository<DraftData, Long> {

//    @Modifying
//    @Query("UPDATE DraftData d SET d.randomClub = :randomClub, d.availablePlayers = :availablePlayers")
//    void initiateDraft(@Param("randomClub") String randomClub, @Param("availablePlayers") List<String> availablePlayers);
//
//    @Modifying
//    @Query("UPDATE DraftData d SET d.selectedPlayersByUser = :selectedPlayers WHERE d.id = :draftId")
//    void selectPlayer(@Param("draftId") Long draftId, @Param("selectedPlayers") Map<String, List<String>> selectedPlayers);
//
//    @Query("SELECT d FROM DraftData d WHERE d.id = :draftId")
//    DraftData getUpdatedDraftData(@Param("draftId") Long draftId);
}

