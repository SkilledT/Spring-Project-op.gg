package leagueoflegendsproject.Models.Database;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Item")
public class Item {

    @Id
    @Column(name = "item_id")
    private Long id;

    @Column(name = "icon_url")
    private String iconUrl;

    @OneToMany
    Set<ParticipantItems> participantItemsSet;

    public Item(Long id, Integer iconId){
        this.id = id;
        this.iconUrl = "http://ddragon.leagueoflegends.com/cdn/11.21.1/img/item/" + iconId + ".png";
        //profileIcon
        //this.iconUrl = "http://ddragon.leagueoflegends.com/cdn/11.21.1/img/profileicon/"+ iconId + ".png";
    }

}
