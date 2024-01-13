package gdsc.hack.influence.dto;

import gdsc.hack.influence.domain.Disease.Disease;
import gdsc.hack.influence.domain.injection.Injection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
public record InjectionResponse (
        Long injectionIdx,
        String injectionName,
        String injectionPeriod,
        String injectionCycle,
        Disease disease,
        boolean isInjected,
        List<Long> friendsInjected,
        List<Long> friendsNotInjected
) {

    public static InjectionResponse of(Injection injection, boolean isInjected, List<Long> friendsInjected, List<Long> friendsNotInjected) {
        return InjectionResponse.builder()
                .injectionIdx(injection.getInjectionIdx())
                .injectionName(injection.getInjectionName())
                .injectionPeriod(injection.getInjectionPeriod())
                .injectionCycle(injection.getInjectionCycle())
                .disease(injection.getDisease())
                .isInjected(isInjected)
                .friendsInjected(friendsInjected)
                .friendsNotInjected(friendsNotInjected)
                .build();
    }
}
