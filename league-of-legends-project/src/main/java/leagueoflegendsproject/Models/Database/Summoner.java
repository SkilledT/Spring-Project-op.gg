package leagueoflegendsproject.Models.Database;


import leagueoflegendsproject.DTOs.SummonersLeagueDto;
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

    @Column(name = "tier")
    private String tier;

    @Column(name = "rank")
    private String rank;

    @Column(name = "league_points")
    private Integer leaguePoints;

    @Column(name = "wins")
    private Integer wins;

    @Column(name = "losses")
    private Integer losses;

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

    public Summoner(SummonersLeagueDto responseSummoner, leagueoflegendsproject.Models.LoLApi.Summoner.SummonerName.Summoner summoner){
        this.summonerId = summoner.getId();
        this.leaguePoints = responseSummoner.getLeaguePoints();
        this.lvl = summoner.getSummonerLevel();
        this.profileIconId = summoner.getProfileIconId();
        this.summonerNickname = responseSummoner.getSummonerName();
        this.tier = responseSummoner.getTier();
        this.rank = responseSummoner.getRank();
        this.wins = responseSummoner.getWins();
        this.losses = responseSummoner.getLoses();
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

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Integer getLeaguePoints() {
        return leaguePoints;
    }

    public void setLeaguePoints(Integer leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getLosses() {
        return losses;
    }

    public void setLosses(Integer losses) {
        this.losses = losses;
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
        return Objects.equals(summonerId, summoner.summonerId) && Objects.equals(lvl, summoner.lvl) && Objects.equals(profileIconId, summoner.profileIconId) && Objects.equals(summonerNickname, summoner.summonerNickname) && Objects.equals(tier, summoner.tier) && Objects.equals(rank, summoner.rank) && Objects.equals(leaguePoints, summoner.leaguePoints) && Objects.equals(wins, summoner.wins) && Objects.equals(losses, summoner.losses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(summonerId, lvl, profileIconId, summonerNickname, tier, rank, leaguePoints, wins, losses);
    }
}
