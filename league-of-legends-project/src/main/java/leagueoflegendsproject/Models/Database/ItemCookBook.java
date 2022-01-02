package leagueoflegendsproject.Models.Database;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Item_cook_book")
public class ItemCookBook {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "item_id")
    @Nullable
    private Item itemMaster;

    @ManyToOne()
    @JoinColumn(name = "component_id")
    @Nullable
    private Item itemComponent;

    public ItemCookBook(Integer id, Item itemMaster, Item itemComponent) {
        this.id = id;
        this.itemMaster = itemMaster;
        this.itemComponent = itemComponent;
    }

    public ItemCookBook() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer key) {
        this.id = key;
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
        return Objects.equals(id, that.id) && Objects.equals(itemMaster, that.itemMaster) && Objects.equals(itemComponent, that.itemComponent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemMaster, itemComponent);
    }
}
