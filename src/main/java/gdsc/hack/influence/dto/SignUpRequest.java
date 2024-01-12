package gdsc.hack.influence.dto;

import gdsc.hack.influence.common.annotation.ValidEnum;
import gdsc.hack.influence.domain.user.Email;
import gdsc.hack.influence.domain.user.Gender;
import gdsc.hack.influence.domain.user.Password;
import gdsc.hack.influence.domain.user.User;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static gdsc.hack.influence.domain.user.Password.ENCODER;

public record SignUpRequest(
        @Size(min = 5, max = 50)
        @NotBlank(message = "이메일은 필수입니다.")
        @Schema(description = "이메일", example = "example@gmail.com")
        String email,

        @Size(min = 8, max = 12, message = "비밀번호는 8자 이상, 12자 이하여야 합니다.")
        @NotBlank(message = "비밀번호는 필수입니다.")
        @Schema(description = "비밀번호", example = "pass1234!")
        String password,

        @Size(min = 2, max = 10, message = "닉네임은 2글자 이상이어야 합니다.")
        @NotBlank(message = "닉네임은 필수입니다.")
        @Schema(description = "닉네임", example = "길동이")
        String nickname,

        @ValidEnum(enumClass = Gender.class)
        @Schema(description = "성별", example = "MALE or FEMALE")
        Gender gender,

        Integer age,

        String address
) {
    public User toUser() {
        return User.registerUser(
                Email.from(email),
                Password.encrypt(password, ENCODER),
                nickname,
                gender,
                age,
                address
        );
    }
}