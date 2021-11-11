package leagueoflegendsproject.Models.Database;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Item")
public class Item {

    @Id
    @Column(name = "item_id")
    private Integer id;

    @Column(name = "icon_url")
    private String iconUrl;

    @OneToMany(mappedBy = "item")
    Set<ParticipantItems> participantItemsSet = new HashSet<>();

    public Item(Integer id){
        this.id = id;
        this.iconUrl = "http://ddragon.leagueoflegends.com/cdn/11.21.1/img/item/" + id + ".png";
        //profileIcon
        //this.iconUrl = "http://ddragon.leagueoflegends.com/cdn/11.21.1/img/profileicon/"+ iconId + ".png";
    }

    public Item(Integer id, String iconUrl, Set<ParticipantItems> participantItemsSet) {
        this.id = id;
        this.iconUrl = iconUrl;
        this.participantItemsSet = participantItemsSet;
    }

    public Item() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Set<ParticipantItems> getParticipantItemsSet() {
        return participantItemsSet;
    }

    public void setParticipantItemsSet(Set<ParticipantItems> participantItemsSet) {
        this.participantItemsSet = participantItemsSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Item{}";
    }
}
