package leagueoflegendsproject.Models.Database;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "summoner")
public class Summoner {

    @Id
    @Column(name = "summoner_id")
    private String summonerId;

    @Column(name = "lvl")
    private Integer lvl;

    @Column(name = "profile_icon_id")
    private Integer profileIconId;

    @Column(name = "summoner_nickname")
    private String summonerNickname;

    @OneToMany(mappedBy = "summoner")
    private Set<MatchParticipant> matchParticipantSet;

    @Override
    public String toString() {
        return "Summoner{" +
                "summonerId='" + summonerId + '\'' +
                ", lvl=" + lvl +
                ", profileIconId=" + profileIconId +
                ", summonerNickname='" + summonerNickname + '\'' +
                ", matchParticipantSet=" + matchParticipantSet +
                '}';
    }
}
