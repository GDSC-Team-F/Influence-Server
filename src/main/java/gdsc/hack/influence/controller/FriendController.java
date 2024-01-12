package gdsc.hack.influence.controller;

import gdsc.hack.influence.common.BaseResponse;
import gdsc.hack.influence.common.annotation.ExtractPayload;
import gdsc.hack.influence.dto.FriendListResponse;
import gdsc.hack.influence.service.FriendService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/friends")
public class FriendController {
    private final FriendService friendService;

    @Operation(summary = "친구 신청", description = "친구 신청")
    @PostMapping("/invite")
    public BaseResponse<Long> invite(@ExtractPayload Long userId,
                                         @RequestBody Map<String, String> email) {
        String responserEmail = email.get("email");
        return new BaseResponse<>(friendService.invite(userId, responserEmail));
    }

    @Operation(summary = "친구 조회", description = "친구 조회")
    @GetMapping("/")
    public BaseResponse<List<FriendListResponse>> get(@ExtractPayload Long userId) {
        return new BaseResponse<>(friendService.get(userId));
    }
}
