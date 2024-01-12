package gdsc.hack.influence.repository;

import gdsc.hack.influence.domain.Disease.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiseaseRepository extends JpaRepository<Disease, Long> {

}
