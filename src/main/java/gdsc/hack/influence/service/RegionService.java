package gdsc.hack.influence.service;

import gdsc.hack.influence.dto.FriendListResponse;
import gdsc.hack.influence.dto.RegionResponse;
import gdsc.hack.influence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RegionService {
    private final UserRepository userRepository;

    @Transactional
    public List<RegionResponse> ranking(Long userId, Long vaccineId) {
        List<RegionResponse> regionResponses = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
//            Long allUser = userRepository.countByAddress(i);
            Long allUser = userRepository.countBy();
            int shotUser = userRepository.getShotUserByAddress(i, vaccineId).size();
            float regionPercent = (shotUser / allUser) * 100.0f;
            regionResponses.add(new RegionResponse(regionPercent));
        }

        return regionResponses;
    }
}
