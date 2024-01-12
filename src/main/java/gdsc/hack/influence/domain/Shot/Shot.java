package gdsc.hack.influence.domain.Shot;

import gdsc.hack.influence.domain.injection.Injection;
import gdsc.hack.influence.domain.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "shot")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Shot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shotIdx;

    @ManyToOne
    @JoinColumn(name = "user_index")
    private User user;

    @ManyToOne
    @JoinColumn(name = "injection_index")
    private Injection injection;

}