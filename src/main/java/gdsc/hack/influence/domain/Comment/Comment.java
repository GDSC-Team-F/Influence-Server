package gdsc.hack.influence.domain.Comment;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import gdsc.hack.influence.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_index")
    private Long commentIdx;

    @Column(name = "disease_name")
    private String diseaseName;

    @NotBlank
    @Column(name = "content", length = 255)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_index")
    private User user;
}