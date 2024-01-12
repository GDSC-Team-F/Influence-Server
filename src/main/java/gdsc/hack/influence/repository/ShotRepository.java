package gdsc.hack.influence.repository;

import gdsc.hack.influence.domain.Shot.Shot;
import gdsc.hack.influence.domain.user.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShotRepository extends JpaRepository<Shot, Long> {
    Long countByUser(User user);
}
