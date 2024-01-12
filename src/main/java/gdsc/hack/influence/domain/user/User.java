package gdsc.hack.influence.domain.user;

import gdsc.hack.influence.common.BaseTimeEntity;
import gdsc.hack.influence.common.util.IntegerArrayConverter;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

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

    private Integer address;

    @Convert(converter = IntegerArrayConverter.class)
    private List<Integer> conditions;

    private Role role;

    @Builder
    private User(Email email, Password password, String nickname, Gender gender, Integer age, Integer address, List<Integer> conditions) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.conditions = conditions;
//        this.character = character;
        this.role = Role.USER;
    }

    public static User registerUser(
            Email email, Password password,
            String nickname, Gender gender, Integer age, Integer address,
            List<Integer> conditions) {
        return new User(email, password, nickname, gender, age, address, conditions);
    }
}
