package gdsc.hack.influence.repository;

import gdsc.hack.influence.domain.injection.Injection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InjectionRepository extends JpaRepository<Injection, Long> {
    Long countBy();
}
