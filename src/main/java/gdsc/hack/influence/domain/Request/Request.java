package gdsc.hack.influence.domain.Request;

import gdsc.hack.influence.domain.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "request")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "requester_id")
    private User requester;

    @ManyToOne
    @JoinColumn(name = "responder_id")
    private User responder;

    @NotBlank
    @Column(name = "invitation_code", length = 255)
    private String invitationCode;

    @NotBlank
    @Column(name = "status", length = 255)
    private String status;

}

