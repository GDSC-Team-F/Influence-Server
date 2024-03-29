package gdsc.hack.influence.controller;

import gdsc.hack.influence.common.BaseResponse;
import gdsc.hack.influence.common.annotation.ExtractPayload;
import gdsc.hack.influence.dto.InjectionResponse;
import gdsc.hack.influence.service.InjectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/injection")
public class InjectionController {
    private final InjectionService injectionService;

    @GetMapping
    public BaseResponse<List<InjectionResponse>> getAll(@ExtractPayload Long userId) {
        List<InjectionResponse> data = injectionService.findAll(userId);

        return new BaseResponse<>(data);
    }

    @PostMapping
    public BaseResponse<Long> vaccinated(@ExtractPayload Long userId,
                                         @RequestParam Long vaccineId) {
        return new BaseResponse<>(injectionService.vaccinated(userId, vaccineId));
    }

}
