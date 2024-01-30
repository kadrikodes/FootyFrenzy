package football.frenzy.dataaccess;

import football.frenzy.entity.DraftData;
import football.frenzy.entity.UserData;
import football.frenzy.entity.UserDraftSelection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDraftSelectionRepository extends JpaRepository<UserDraftSelection, Long> {
    List<UserDraftSelection> findByUserIdAndDraftId(UserData userId, DraftData draftId);
//    List<UserDraftSelection> findByUserIdAndDraftId(Long userId, Long draftId);

}
