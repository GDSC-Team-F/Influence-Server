package gdsc.hack.influence.controller;

import gdsc.hack.influence.common.BaseResponse;
import gdsc.hack.influence.common.annotation.ExtractPayload;
import gdsc.hack.influence.dto.LoginRequest;
import gdsc.hack.influence.dto.LoginResponse;
import gdsc.hack.influence.dto.MyPageResponse;
import gdsc.hack.influence.dto.SignUpRequest;
import gdsc.hack.influence.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @Operation(summary = "회원가입", description = "사용자가 회원가입을 진행합니다.")
    @PostMapping("/register")
    public BaseResponse<Long> signUp(@RequestBody @Valid SignUpRequest request) {
        return new BaseResponse<>(userService.signUp(request.toUser()));
    }

    @Operation(summary = "로그인", description = "사용자가 로그인을 진행합니다.")
    @PostMapping("/login")
    public BaseResponse<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        return new BaseResponse<>(userService.login(request.email(), request.password()));
    }

    @Operation(summary = "마이페이지", description = "마이페이지")
    @GetMapping("/me")
    public BaseResponse<MyPageResponse> login(@ExtractPayload Long userId) {
        return new BaseResponse<>(userService.getInfo(userId));
    }
}
