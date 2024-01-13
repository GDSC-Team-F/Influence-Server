package gdsc.hack.influence.repository;

import gdsc.hack.influence.domain.Request.Friend;
import gdsc.hack.influence.domain.point.Point;
import gdsc.hack.influence.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointRepository extends JpaRepository<Point, Long> {
    List<Point> findByPointed(User pointed);
}
