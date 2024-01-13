package gdsc.hack.influence.domain.Shot;

import gdsc.hack.influence.domain.injection.Injection;
import gdsc.hack.influence.domain.point.Point;
import gdsc.hack.influence.domain.user.User;
import lombok.*;

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

    @Builder
    public Shot(User user, Injection injection) {
        this.user = user;
        this.injection = injection;
    }

    public static Shot create(User user, Injection injection) {
        return new Shot(user, injection);
    }
}