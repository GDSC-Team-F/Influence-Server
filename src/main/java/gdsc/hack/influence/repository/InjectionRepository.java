package gdsc.hack.influence.repository;

import gdsc.hack.influence.domain.injection.Injection;
import gdsc.hack.influence.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InjectionRepository extends JpaRepository<Injection, Long> {
    Long countBy();

    Optional<Injection> findById(Long id);
}
