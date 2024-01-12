package gdsc.hack.influence.domain.point;

import gdsc.hack.influence.domain.injection.Injection;
import gdsc.hack.influence.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "point")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pointIdx;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pointing_id")
    private User pointing;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pointed_id")
    private User pointed;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "injection_id")
    private Injection injection;

    @Builder
    public Point(User pointing, User pointed, Injection injection) {
        this.pointing = pointing;
        this.pointed = pointed;
        this.injection = injection;
    }

    public static Point create(User pointing, User pointed, Injection injection) {
        return new Point(pointing, pointed, injection);
    }
}
