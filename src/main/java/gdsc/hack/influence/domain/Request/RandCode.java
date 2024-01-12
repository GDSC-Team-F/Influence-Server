package gdsc.hack.influence.domain.Request;

import gdsc.hack.influence.domain.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "randcode")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RandCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestIdx;

    @Column(name = "randcode", length = 255)
    private String randCode;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public RandCode(String invitationCode, User user) {
        this.randCode = invitationCode;
        this.user = user;
    }

    public static RandCode create(String invitationCode, User user) {
        return new RandCode(invitationCode, user);
    }
}
