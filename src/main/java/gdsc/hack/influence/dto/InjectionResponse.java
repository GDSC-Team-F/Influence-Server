package gdsc.hack.influence.dto;

import gdsc.hack.influence.domain.Disease.Disease;
import gdsc.hack.influence.domain.injection.Injection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record InjectionResponse (
        Long injectionIdx,
        String injectionName,
        String injectionPeriod,
        String injectionCycle,
        Disease disease,
        boolean isInjected,
        String friendsInjected,
        String friendsNotInjected
) {

    public static InjectionResponse of(Injection injection, boolean isInjected, String friendsInjected, String friendsNotInjected) {
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
