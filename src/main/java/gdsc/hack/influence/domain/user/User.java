package gdsc.hack.influence.domain.user;

import gdsc.hack.influence.common.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @NotBlank
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotBlank
    private Integer age;

    @NotBlank
    private String address;

    private Role role;
}
