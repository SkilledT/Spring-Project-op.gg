package leagueoflegendsproject.Models.Database.Keys;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemCookBookKey implements Serializable {

    Integer itemId;
    Integer componentId;

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
