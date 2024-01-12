package gdsc.hack.influence.service;

import gdsc.hack.influence.domain.injection.Injection;
import gdsc.hack.influence.domain.point.Point;
import gdsc.hack.influence.domain.user.User;
import gdsc.hack.influence.dto.FriendListResponse;
import gdsc.hack.influence.dto.PointRequest;
import gdsc.hack.influence.dto.PointResponse;
import gdsc.hack.influence.repository.InjectionRepository;
import gdsc.hack.influence.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PointService {
    private final UserFindService userFindService;
    private final InjectionRepository injectionRepository;
    private final PointRepository pointRepository;

    @Transactional
    public Long point(Long userId, PointRequest request) {
        User pointing = userFindService.findByMemId(userId);
        User pointed = userFindService.findByMemId(request.friendsId());
        Injection injection = injectionRepository.findById(request.vaccineId()).get();

        Point point = Point.create(pointing, pointed, injection);
        Point savedPoint = pointRepository.save(point);

        return savedPoint.getPointIdx();
    }

    @Transactional
    public List<PointResponse> checkPoint(Long userId) {
        User pointed = userFindService.findByMemId(userId);
        List<Point> pointings = pointRepository.findByPointed(pointed);
        int size = pointings.size();

        List<PointResponse> pointLists = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            pointLists.add(new PointResponse(
                    pointings.get(i).getPointIdx(),
                    pointings.get(i).getPointing().getUserIdx(),
                    pointings.get(i).getInjection().getInjectionIdx()
            ));
        }

        return pointLists;
    }

    @Transactional
    public Long delete(Long userId, Long id) {
        System.out.println(pointRepository.findById(id).get().getPointed().getUserIdx().equals(userId));
        pointRepository.deleteById(id);

        return id;
    }
}
