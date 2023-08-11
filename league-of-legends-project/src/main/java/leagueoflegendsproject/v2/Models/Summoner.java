package leagueoflegendsproject.v2.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Summoner {

    @Id
    private Long id;

    private String summonerId;
    private Integer lvl;
    private Integer profileIconId;
    private String summonerNickname;
    private String tier;
    private String rank;
    private Integer leaguePoints;
    private Integer wins;
    private Integer losses;


    @OneToMany(mappedBy = "summoner", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<MatchParticipant> matchParticipantSet = new HashSet<>();

}
