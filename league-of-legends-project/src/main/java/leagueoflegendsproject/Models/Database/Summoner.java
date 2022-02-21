package leagueoflegendsproject.Models.Database;


import leagueoflegendsproject.Models.LoLApi.Matches.matchId.ParticipantsItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
    private Set<MatchParticipant> matchParticipantSet = new HashSet<>();

    public Summoner(ParticipantsItem participantsItem){
        this.summonerId = participantsItem.getSummonerId();
        this.lvl = participantsItem.getSummonerLevel();
        this.profileIconId = participantsItem.getProfileIcon();
        this.summonerNickname = participantsItem.getSummonerName();
    }

    public Summoner(leagueoflegendsproject.Models.LoLApi.Summoner.SummonerName.Summoner responseSummoner){
        this.summonerId = responseSummoner.getId();
        this.lvl = responseSummoner.getSummonerLevel();
        this.profileIconId = responseSummoner.getProfileIconId();
        this.summonerNickname = responseSummoner.getName();
    }

    public Summoner(){}

    public String getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId;
    }

    public Integer getLvl() {
        return lvl;
    }

    public void setLvl(Integer lvl) {
        this.lvl = lvl;
    }

    public Integer getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(Integer profileIconId) {
        this.profileIconId = profileIconId;
    }

    public String getSummonerNickname() {
        return summonerNickname;
    }

    public void setSummonerNickname(String summonerNickname) {
        this.summonerNickname = summonerNickname;
    }

    public Set<MatchParticipant> getMatchParticipantSet() {
        return matchParticipantSet;
    }

    public void setMatchParticipantSet(Set<MatchParticipant> matchParticipantSet) {
        this.matchParticipantSet = matchParticipantSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Summoner summoner = (Summoner) o;
        return Objects.equals(summonerId, summoner.summonerId) &&
                Objects.equals(lvl, summoner.lvl) &&
                Objects.equals(profileIconId, summoner.profileIconId) &&
                Objects.equals(summonerNickname, summoner.summonerNickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(summonerId, lvl, profileIconId, summonerNickname);
    }
}
