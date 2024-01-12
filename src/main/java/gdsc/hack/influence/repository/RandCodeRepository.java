package gdsc.hack.influence.repository;

import gdsc.hack.influence.domain.Request.RandCode;
import gdsc.hack.influence.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RandCodeRepository extends JpaRepository<RandCode, Long> {
    Optional<RandCode> findByRandCode(String randCode);
}
