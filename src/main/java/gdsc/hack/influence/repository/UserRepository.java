package gdsc.hack.influence.repository;

import gdsc.hack.influence.domain.user.Email;
import gdsc.hack.influence.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByNickname(String nickname);
    Optional<User> findByEmail(Email memEmail);
    Optional<User> findById(Long id);
    Long countByAddress(Integer address);

    Long countBy();

    @Query("SELECT u, s FROM User u LEFT JOIN Shot s ON s.user = u WHERE u.address = :address AND s.injection.injectionIdx = :injectionIdx")
    List<Object[]> getShotUserByAddress(@Param("address") Integer address,
                                        @Param("injectionIdx") Long injectionIdx);
}
