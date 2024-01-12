package gdsc.hack.influence.repository;

import gdsc.hack.influence.domain.Request.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<Friend, Long> {
}
