package gdsc.hack.influence.domain.Request;

import gdsc.hack.influence.domain.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "request")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestIdx;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "requester_id")
    private User requester;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "responder_id")
    private User responder;

    @Column(name = "randcode", length = 255)
    private String randCode;

    @Column(name = "status", length = 255)
    private String status;

    @Builder
    public Friend(User requester, User responder, String randCode) {
        this.requester = requester;
        this.responder = responder;
        this.randCode = randCode;
        this.status = "TRUE";
    }

    public static Friend create(User requester, User responder, String randCode) {
        return new Friend(requester, responder, randCode);
    }
}

