package leagueoflegendsproject.Models.Database;


import leagueoflegendsproject.DTOs.SummonersLeagueDto;
import leagueoflegendsproject.Helpers.RiotLinksProvider;
import leagueoflegendsproject.Models.LoLApi.League.EncryptedSummonerId.SummonerLeagueResponseItem;
import leagueoflegendsproject.Models.LoLApi.Matches.matchId.ParticipantsItem;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "summoner")
@Builder(setterPrefix = "with")
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

    @OneToMany(mappedBy = "summoner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<MatchParticipant> matchParticipantSet = new HashSet<>();

    public void addMatchParticipantChild(MatchParticipant matchParticipant) {
        this.matchParticipantSet.add(matchParticipant);
        matchParticipant.setSummoner(this);
    }

    public Summoner(SummonerLeagueResponseItem item) {
        this.summonerId = item.getSummonerId();
        this.lvl = 100;
        this.profileIconId = 123;
        this.summonerNickname = item.getSummonerName();
        this.tier = item.getTier();
        this.rank = item.getRank();
        this.leaguePoints = item.getLeaguePoints();
        this.wins = item.getWins();
        this.losses = item.getLosses();
    }

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

    public static Summoner getInvalidSummoner() {
        var summoner = new Summoner();
        summoner.summonerId = "fakeId";
        summoner.lvl = 100;
        summoner.profileIconId = 100;
        summoner.summonerNickname = "InvalidSummonerName-1";
        summoner.wins = 0;
        summoner.losses = 0;
        summoner.leaguePoints = 0;
        summoner.rank = "Diamond";
        summoner.tier = "I";
        return summoner;
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

    @Override
    public String toString() {
        return "Summoner{" +
                "summonerId='" + summonerId + '\'' +
                ", summonerNickname='" + summonerNickname + '\'' +
                '}';
    }
}
