package leagueoflegendsproject.v2.Models;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChampionImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String sprite;
    private String groupName;

    private Integer x;
    private Integer y;
    private Integer w;
    private Integer h;

    @OneToOne(mappedBy = "image")
    @JoinColumn
    private ChampionSnapshot championSnapshot;
}
