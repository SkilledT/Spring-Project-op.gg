package leagueoflegendsproject.Models.Database;


import leagueoflegendsproject.Models.Database.Keys.ParticipantItemsKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "participant_items")
public class ParticipantItems {

    @EmbeddedId
    private ParticipantItemsKey key;

    @JoinColumn(name = "item_id")
    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId(value = "itemId")
    private Item item;


    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId(value = "matchParticipantKey")
    @JoinColumns({
            @JoinColumn(name = "summoner_id", referencedColumnName = "Summoner_summoner_id"),
            @JoinColumn(name = "match_id", referencedColumnName = "Match_match_Id")
    })
    private MatchParticipant matchParticipant;


    public void setMatchParticipant(MatchParticipant matchParticipant) {
        this.matchParticipant = matchParticipant;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
