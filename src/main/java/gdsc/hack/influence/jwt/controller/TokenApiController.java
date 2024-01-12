package gdsc.hack.influence.jwt.controller;

import gdsc.hack.influence.common.BaseResponse;
import gdsc.hack.influence.common.annotation.ExtractPayload;
import gdsc.hack.influence.common.annotation.ExtractToken;
import gdsc.hack.influence.jwt.dto.TokenResponse;
import gdsc.hack.influence.jwt.service.TokenReissueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/token")
public class TokenApiController {
    private final TokenReissueService tokenReissueService;

    @Operation(summary = "refresh token 재발급", description = "refresh token을 재발급합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "요청에 성공했습니다."),
            @ApiResponse(responseCode = "401", description = "유효하지 않은 토큰입니다."),
            @ApiResponse(responseCode = "500", description = "서버와의 연결에 실패했습니다.")
    })
    @PostMapping("/reissue")
    public BaseResponse<TokenResponse> reissueTokens(@ExtractPayload Long memId, @ExtractToken String refreshToken) {
        TokenResponse tokenResponse = tokenReissueService.reissueTokens(memId, refreshToken);
        return new BaseResponse<>(tokenResponse);
    }
}