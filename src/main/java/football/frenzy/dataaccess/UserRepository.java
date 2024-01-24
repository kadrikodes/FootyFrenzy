package football.frenzy.dataaccess;

import football.frenzy.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends ListCrudRepository<UserData, Long> {
    Optional<UserData> findByUserName(String userName);
}
