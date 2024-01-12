package gdsc.hack.influence.repository;

import gdsc.hack.influence.domain.user.Email;
import gdsc.hack.influence.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByNickname(String nickname);
    Optional<User> findByEmail(Email memEmail);
    Optional<User> findById(Long id);
}
