package leagueoflegendsproject.Models.Database;


import leagueoflegendsproject.Models.LoLApi.Matches.matchId.Match;
import leagueoflegendsproject.Models.LoLApi.Matches.matchId.ParticipantsItem;
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
@Table(name = "Champion")
public class Champion {

    @Id
    @Column(name = "champion_id")
    private Long id;

    private String name;

    @Column(name = "icon_url")
    private String iconUrl;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "champion")
    private Set<MatchParticipant> matchParticipantSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "champion")
    private Set<Ban> ban;

    public Champion(ParticipantsItem participantsItem){
        this.id = (long) participantsItem.getChampionId();
        this.name = participantsItem.getChampionName();
        this.iconUrl = "http://ddragon.leagueoflegends.com/cdn/11.21.1/img/champion/" + participantsItem.getChampionName() +".png";
    }

    public Champion(Long id, String name) {
        this.id = id;
        this.name = name;
        this.iconUrl = "http://ddragon.leagueoflegends.com/cdn/11.21.1/img/champion/" + name +".png";
    }
}
