package leagueoflegendsproject.Models;

import leagueoflegendsproject.Models.Database.Item;
import leagueoflegendsproject.Models.Database.Keys.ItemCookBookKey;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "item_cook_book")
public class ItemCookBook {

    @EmbeddedId
    ItemCookBookKey key = new ItemCookBookKey();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    @MapsId(value = "itemId")
    Item itemMaster;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "component_id")
    @MapsId(value = "componentId")
    Item itemComponent;

    public ItemCookBook(ItemCookBookKey key, Item itemMaster, Item itemComponent) {
        this.key = key;
        this.itemMaster = itemMaster;
        this.itemComponent = itemComponent;
    }

    public ItemCookBook() {
    }

    public ItemCookBookKey getKey() {
        return key;
    }

    public void setKey(ItemCookBookKey key) {
        this.key = key;
    }

    public Item getItemMaster() {
        return itemMaster;
    }

    public void setItemMaster(Item itemMaster) {
        this.itemMaster = itemMaster;
    }

    public Item getItemComponent() {
        return itemComponent;
    }

    public void setItemComponent(Item itemComponent) {
        this.itemComponent = itemComponent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemCookBook that = (ItemCookBook) o;
        return Objects.equals(key, that.key) && Objects.equals(itemMaster, that.itemMaster) && Objects.equals(itemComponent, that.itemComponent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, itemMaster, itemComponent);
    }
}
