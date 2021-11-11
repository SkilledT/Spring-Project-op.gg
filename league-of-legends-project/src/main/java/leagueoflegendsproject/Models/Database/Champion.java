package leagueoflegendsproject.Models.Database;


import leagueoflegendsproject.Models.LoLApi.Matches.matchId.Match;
import leagueoflegendsproject.Models.LoLApi.Matches.matchId.ParticipantsItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "Champion")
public class Champion {

    @Id
    @Column(name = "champion_id")
    private Integer id;

    private String name;

    @Column(name = "icon_url")
    private String iconUrl;

    @OneToMany(mappedBy = "champion")
    private Set<MatchParticipant> matchParticipantSet = new HashSet<>();

    @OneToMany(mappedBy = "champion")
    private Set<Ban> ban = new HashSet<>();

    public Champion(ParticipantsItem participantsItem){
        this.id = participantsItem.getChampionId();
        this.name = participantsItem.getChampionName();
        this.iconUrl = "http://ddragon.leagueoflegends.com/cdn/11.21.1/img/champion/" + participantsItem.getChampionName() +".png";
    }

    public Champion(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.iconUrl = "http://ddragon.leagueoflegends.com/cdn/11.21.1/img/champion/" + name +".png";
    }

    public Champion(Integer id, String name, String iconUrl, Set<MatchParticipant> matchParticipantSet, Set<Ban> ban) {
        this.id = id;
        this.name = name;
        this.iconUrl = iconUrl;
        this.matchParticipantSet = matchParticipantSet;
        this.ban = ban;
    }

    public Champion() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Set<MatchParticipant> getMatchParticipantSet() {
        return matchParticipantSet;
    }

    public void setMatchParticipantSet(Set<MatchParticipant> matchParticipantSet) {
        this.matchParticipantSet = matchParticipantSet;
    }

    public Set<Ban> getBan() {
        return ban;
    }

    public void setBan(Set<Ban> ban) {
        this.ban = ban;
    }

    public void update(ParticipantsItem participantsItem){
        this.name = participantsItem.getChampionName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Champion champion = (Champion) o;
        return Objects.equals(id, champion.id) &&
                Objects.equals(name, champion.name) &&
                Objects.equals(iconUrl, champion.iconUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, iconUrl);
    }
}
