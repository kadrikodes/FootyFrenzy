package football.frenzy.dataaccess;

import football.frenzy.entity.UserDraftSelection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDraftSelectionRepository extends JpaRepository<UserDraftSelection, Long> {
    List<UserDraftSelection> findByUserIdAndDraftId(Long userId, Long draftId);
}
