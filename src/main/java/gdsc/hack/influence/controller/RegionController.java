package gdsc.hack.influence.controller;

import gdsc.hack.influence.common.BaseResponse;
import gdsc.hack.influence.common.annotation.ExtractPayload;
import gdsc.hack.influence.dto.PointRequest;
import gdsc.hack.influence.dto.RegionResponse;
import gdsc.hack.influence.service.RegionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/region")
public class RegionController {
    private final RegionService regionService;

    @Operation(summary = "지역 랭킹", description = "지역 랭킹")
    @GetMapping("/{vaccineId}")
    public BaseResponse<List<RegionResponse>> point(@ExtractPayload Long userId,
                                                    @PathVariable Long vaccineId) {
        return new BaseResponse<>(regionService.ranking(userId, vaccineId));
    }
}
