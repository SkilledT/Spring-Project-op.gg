package leagueoflegendsproject.v2.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ban {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer pickTurn;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private MatchParticipant matchParticipant;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ChampionSnapshot championSnapshot;

}
