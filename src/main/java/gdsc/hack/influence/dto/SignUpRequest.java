package gdsc.hack.influence.dto;

import gdsc.hack.influence.common.annotation.ValidEnum;
import gdsc.hack.influence.domain.user.Email;
import gdsc.hack.influence.domain.user.Gender;
import gdsc.hack.influence.domain.user.Password;
import gdsc.hack.influence.domain.user.User;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.List;

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

        @Size(min = 2, max = 10, message = "이름은 2글자 이상이어야 합니다.")
        @NotBlank(message = "이름은 필수입니다.")
        @Schema(description = "이름", example = "길동이")
        String name,

        @ValidEnum(enumClass = Gender.class)
        @Schema(description = "성별", example = "0(MALE) or 1(FEMALE)")
        Gender gender,

        Integer age,

        Integer address,

        List<Integer> conditions
) {
    public User toUser() {
        return User.registerUser(
                Email.from(email),
                Password.encrypt(password, ENCODER),
                name,
                gender,
                age,
                address,
                conditions
        );
    }
}