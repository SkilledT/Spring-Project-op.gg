package leagueoflegendsproject.Models.Database;


import leagueoflegendsproject.Models.Database.Keys.ParticipantItemsKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "participant_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantItems {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "item_id")
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Item item;


    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumns({
            @JoinColumn(name = "summoner_id", referencedColumnName = "Summoner_summoner_id"),
            @JoinColumn(name = "match_id", referencedColumnName = "Match_match_Id")
    })
    private MatchParticipant matchParticipant;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParticipantItems that = (ParticipantItems) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(item, that.item) &&
                Objects.equals(matchParticipant, that.matchParticipant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, item, matchParticipant);
    }

    @Override
    public String toString() {
        return "ParticipantItems{}";
    }
}
