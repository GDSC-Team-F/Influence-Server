package gdsc.hack.influence.controller;

import gdsc.hack.influence.common.BaseResponse;
import gdsc.hack.influence.common.annotation.ExtractPayload;
import gdsc.hack.influence.service.FriendService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/friends")
public class FriendController {
    private final FriendService friendService;

    @Operation(summary = "친구 신청 코드 요청", description = "친구 신청 코드 요청")
    @GetMapping("/")
    public BaseResponse<String> generate(@ExtractPayload Long userId) {
        return new BaseResponse<>(friendService.generate(userId));
    }

    @Operation(summary = "친구 신청 코드 요청", description = "친구 신청 코드 요청")
    @PostMapping("/{randCode}")
    public BaseResponse<Long> request(@ExtractPayload Long userId, @PathVariable String randCode) {
        return new BaseResponse<>(friendService.request(userId, randCode));
    }

}
