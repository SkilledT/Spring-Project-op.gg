package leagueoflegendsproject.Models.Database.Keys;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ItemCookBookKey implements Serializable {

    @Column(name = "item_id")
    Integer itemId;
    @Column(name = "component_id")
    Integer componentId;

    public ItemCookBookKey(Integer itemId, Integer componentId) {
        this.itemId = itemId;
        this.componentId = componentId;
    }

    public ItemCookBookKey() {
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getComponentId() {
        return componentId;
    }

    public void setComponentId(Integer componentId) {
        this.componentId = componentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemCookBookKey that = (ItemCookBookKey) o;
        return Objects.equals(itemId, that.itemId) && Objects.equals(componentId, that.componentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, componentId);
    }
}
