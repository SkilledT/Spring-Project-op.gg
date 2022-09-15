package leagueoflegendsproject.Models.Database;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Item_cook_book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
