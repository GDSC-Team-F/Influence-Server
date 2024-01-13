package gdsc.hack.influence.dto;

import gdsc.hack.influence.domain.user.Gender;

import java.util.List;

public record MyPageResponse(
        Long userIdx,

        String name,

        String email,

        List<Integer> conditions,

        Integer address,

        Gender gender,

        Integer age,

        Integer image,

        Float myPercent
) {
}
