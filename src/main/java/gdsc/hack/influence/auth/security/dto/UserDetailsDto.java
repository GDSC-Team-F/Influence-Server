package gdsc.hack.influence.auth.security.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gdsc.hack.influence.domain.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserDetailsDto {
    private Long userIdx;
    @JsonIgnore
    private String email;
    @JsonIgnore
    private String password;
    private String nickname;
    private List<String> role;

    public UserDetailsDto(User user) {
        this.userIdx = user.getUserIdx();
        this.email = user.getEmail().getValue();
        this.password = user.getPassword().getValue();
        this.nickname = user.getNickname();
        this.role = List.of(user.getRole().getAuthority());
    }
}