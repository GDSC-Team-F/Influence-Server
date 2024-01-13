package gdsc.hack.influence.controller;

import gdsc.hack.influence.common.BaseResponse;
import gdsc.hack.influence.common.annotation.ExtractPayload;
import gdsc.hack.influence.dto.PointRequest;
import gdsc.hack.influence.dto.PointResponse;
import gdsc.hack.influence.service.PointService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/point")
public class PointController {
    private final PointService pointService;

    @Operation(summary = "찌르기", description = "찌르기")
    @PostMapping("")
    public BaseResponse<Long> point(@ExtractPayload Long userId,
                                     @RequestBody PointRequest request) {
        return new BaseResponse<>(pointService.point(userId, request));
    }

    @Operation(summary = "찌르기한 유저", description = "찌르기한 유저")
    @GetMapping("")
    public BaseResponse<List<PointResponse>> checkPoint(@ExtractPayload Long userId) {
        return new BaseResponse<>(pointService.checkPoint(userId));
    }

    @Operation(summary = "찌르기 확인", description = "찌르기 확인")
    @DeleteMapping("")
    public BaseResponse<Long> delete(@ExtractPayload Long userId,
                                     @RequestParam Long id) {
        return new BaseResponse<>(pointService.delete(userId, id));
    }
}
