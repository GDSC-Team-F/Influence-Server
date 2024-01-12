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

    @Column(name = "status", length = 255)
    private String status;

    @Builder
    public Friend(User requester, User responder) {
        this.requester = requester;
        this.responder = responder;
        this.status = "TRUE";
    }

    public static Friend create(User requester, User responder) {
        return new Friend(requester, responder);
    }
}

