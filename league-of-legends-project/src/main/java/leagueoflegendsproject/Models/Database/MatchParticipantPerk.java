package leagueoflegendsproject.Models.Database;

import leagueoflegendsproject.Models.Database.Keys.MatchParticipantPerkKey;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Match_Participant_Perk")
public class MatchParticipantPerk {

    @EmbeddedId
    private MatchParticipantPerkKey matchParticipantPerkKey = new MatchParticipantPerkKey();

    @MapsId("matchParticipantKey")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "participant_match_summoner_id", referencedColumnName = "Summoner_summoner_id"),
            @JoinColumn(name = "participant_match_match_id", referencedColumnName = "Match_match_id")
    })
    private MatchParticipant matchParticipant;

    @MapsId("perkId")
    @JoinColumn(name = "perk_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Perk perk;

    public MatchParticipantPerk() {
    }

    public MatchParticipantPerk(MatchParticipantPerkKey matchParticipantPerkKey, MatchParticipant matchParticipant, Perk perk) {
        this.matchParticipantPerkKey = matchParticipantPerkKey;
        this.matchParticipant = matchParticipant;
        this.perk = perk;
    }

    public MatchParticipantPerkKey getMatchParticipantPerkKey() {
        return matchParticipantPerkKey;
    }

    public void setMatchParticipantPerkKey(MatchParticipantPerkKey matchParticipantPerkKey) {
        this.matchParticipantPerkKey = matchParticipantPerkKey;
    }

    public MatchParticipant getMatchParticipant() {
        return matchParticipant;
    }

    public void setMatchParticipant(MatchParticipant matchParticipant) {
        this.matchParticipant = matchParticipant;
    }

    public Perk getPerk() {
        return perk;
    }

    public void setPerk(Perk perk) {
        this.perk = perk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchParticipantPerk that = (MatchParticipantPerk) o;
        return Objects.equals(matchParticipantPerkKey, that.matchParticipantPerkKey) &&
                Objects.equals(matchParticipant, that.matchParticipant) &&
                Objects.equals(perk, that.perk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchParticipantPerkKey, matchParticipant, perk);
    }
}