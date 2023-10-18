package leagueoflegendsproject.v2.Models;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChampionInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer attack;
    private Integer defense;
    private Integer magic;
    private Integer difficulty;

    @OneToOne(mappedBy = "info")
    @JoinColumn
    private ChampionSnapshot championSnapshot;
}
