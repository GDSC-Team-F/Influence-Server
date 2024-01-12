package gdsc.hack.influence.domain.injection;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "injection")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Injection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "injection_idx")
    private Long injectionIdx;

    @Column(name = "injection_name")
    private String injectionName;

    @Column(name = "injection_period")
    private String injectionPeriod;

    @Column(name = "injection_cycle")
    private String injectionCycle;

    @ManyToOne
    @JoinColumn(name = "desease_idx")
    private Injection injection;
}

