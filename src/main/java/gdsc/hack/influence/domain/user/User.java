package gdsc.hack.influence.domain.user;

import gdsc.hack.influence.common.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user")
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userIdx;

    @Embedded
    private Email email;

    @Embedded
    private Password password;

    private String nickname;

    private Gender gender;

    private Integer age;

    @NotBlank
    private String address;

    private Role role;

    @Builder
    private User(Email email, Password password, String nickname, Gender gender, Integer age, String address) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.role = Role.USER;
    }

    public static User registerUser(
            Email email, Password password, String nickname, Gender gender, Integer age, String address) {
        return new User(email, password, nickname, gender, age, address);
    }
}
