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
    private MatchParticipant matchParticipant;
}
