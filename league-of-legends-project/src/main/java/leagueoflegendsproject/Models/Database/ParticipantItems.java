package leagueoflegendsproject.Models.Database;


import leagueoflegendsproject.Models.Database.Keys.MatchParticipantKey;
import leagueoflegendsproject.Models.Database.Keys.ParticipantItemsKey;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "participant_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantItems {

    @EmbeddedId
    private ParticipantItemsId id = new ParticipantItemsId();

    @JoinColumn(name = "item_id")
    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId(value = "itemId")
    private Item item;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "summoner_id", referencedColumnName = "Summoner_summoner_id"),
            @JoinColumn(name = "match_id", referencedColumnName = "Match_match_Id")
    })
    @MapsId(value = "matchParticipantKey")
    private MatchParticipant matchParticipant;


    public void setPosition(Integer position) {
        this.id.setPosition(position);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParticipantItems that = (ParticipantItems) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class ParticipantItemsId implements Serializable {

    @Embedded
    private MatchParticipantKey matchParticipantKey = new MatchParticipantKey();
    private Integer itemId;
    private Integer position;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParticipantItemsId that = (ParticipantItemsId) o;
        return Objects.equals(matchParticipantKey, that.matchParticipantKey) && Objects.equals(itemId, that.itemId) && Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchParticipantKey, itemId, position);
    }
}
