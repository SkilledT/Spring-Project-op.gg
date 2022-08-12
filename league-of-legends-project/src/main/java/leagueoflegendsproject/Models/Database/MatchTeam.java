package leagueoflegendsproject.Models.Database;


import leagueoflegendsproject.Models.Database.Keys.MatchTeamKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "match_team")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchTeam {

    @EmbeddedId
    private MatchTeamKey key = new MatchTeamKey();

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "match_id")
    @MapsId(value = "matchId")
    private Match match;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "team_id")
    @MapsId(value = "teamId")
    private Team team;

    @OneToMany(mappedBy = "matchTeam", cascade = CascadeType.ALL)
    private Set<TeamObjective> teamObjectiveSet = new HashSet<>();

    @OneToMany(mappedBy = "matchTeam", cascade = CascadeType.ALL)
    private Set<Ban> banSet = new HashSet<>();

    public void addTeamObjectChild(TeamObjective teamObjective) {
        this.teamObjectiveSet.add(teamObjective);
        teamObjective.setMatchTeam(this);
    }

    public void addBanChild(Ban ban) {
        this.banSet.add(ban);
        ban.setMatchTeam(this);
    }


    public MatchTeam(Match match, Team team) {
        this.match = match;
        this.team = team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchTeam matchTeam = (MatchTeam) o;
        return Objects.equals(key, matchTeam.key) && Objects.equals(match, matchTeam.match) && Objects.equals(team, matchTeam.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, match, team);
    }

    @Override
    public String toString() {
        return "MatchTeam{" +
                "key=" + key +
                ", match=" + match +
                ", team=" + team +
                ", teamObjectiveSet=" + teamObjectiveSet +
                ", banSet=" + banSet +
                '}';
    }
}
