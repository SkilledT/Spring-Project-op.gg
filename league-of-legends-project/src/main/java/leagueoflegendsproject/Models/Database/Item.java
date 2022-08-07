package leagueoflegendsproject.Models.Database;


import com.google.gson.annotations.SerializedName;
import leagueoflegendsproject.Helpers.RiotLinksProvider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "basicItemMapping",
                classes = @ConstructorResult(
                        targetClass = Item.class,
                        columns = {
                                @ColumnResult(name = "item_id", type = Integer.class),
                                @ColumnResult(name = "icon_url", type = String.class),
                                @ColumnResult(name = "description", type = String.class),
                                @ColumnResult(name = "plain_text", type = String.class),
                                @ColumnResult(name = "total_cost", type = Integer.class),
                                @ColumnResult(name = "sell", type = Integer.class),
                                @ColumnResult(name = "base_cost", type = Integer.class),
                                @ColumnResult(name = "name", type = String.class),
                        }
                )
        )
})
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                procedureName = "get_most_popular_items_for_champion",
                name = "mostPopularItemsForChampion",
                parameters = {
                        @StoredProcedureParameter(name = "championName", type = String.class, mode = ParameterMode.IN)
                }
        )
})
public class Item {

    @Id
    @Column(name = "item_id")
    @SerializedName(value = "item_id")
    private Integer id;

    @Column(name = "icon_url", length = 10000)
    @Nullable
    @SerializedName(value = "icon_url")
    private String iconUrl;

    @Column(name = "description", length = 10000)
    @Nullable
    @SerializedName(value = "description")
    private String description;

    @Column(name = "plain_text", length = 10000)
    @Nullable
    @SerializedName(value = "plain_text")
    private String plainText;

    @Column(name = "total_cost")
    @Nullable
    @SerializedName(value = "total_cost")
    private Integer totalCost;

    @Column(name = "sell")
    @Nullable
    @SerializedName(value = "sell")
    private Integer sell;

    @Column(name = "base_cost")
    @Nullable
    @SerializedName(value = "base_cost")
    private Integer baseCost;

    @Column(name = "name", length = 10000)
    @Nullable
    @SerializedName(value = "name")
    private String name;

    @OneToMany(mappedBy = "item")
    Set<ParticipantItems> participantItemsSet = new HashSet<>();

    @OneToMany(mappedBy = "itemComponent")
    Set<ItemCookBook> itemComponentSet = new HashSet<>();

    @OneToMany(mappedBy = "itemMaster")
    Set<ItemCookBook> itemMasterSet = new HashSet<>();

    public Item(Integer id){
        this.id = id;
        this.iconUrl = "https://ddragon.leagueoflegends.com/cdn/" + RiotLinksProvider.RIOT_VERSION + "/img/item/" + id + ".png";
    }

    public Item(Integer id, String iconUrl, Set<ParticipantItems> participantItemsSet) {
        this.id = id;
        this.iconUrl = iconUrl;
        this.participantItemsSet = participantItemsSet;
    }

    public Item(leagueoflegendsproject.Models.LoLApi.Items.Item item) {
        this.id = item.getId();
        this.iconUrl = item.getImage().getFull();
        this.description = item.getDescription();
        this.plainText = item.getPlaintext();
        this.totalCost = item.getGold().getTotal();
        this.sell = item.getGold().getSell();
        this.baseCost = item.getGold().getBase();
        this.name = item.getName();
    }

    public Item(Integer id, String iconUrl, String description, String plainText, Integer totalCost, Integer sell, Integer baseCost) {
        this.id = id;
        this.iconUrl = iconUrl;
        this.description = description;
        this.plainText = plainText;
        this.totalCost = totalCost;
        this.sell = sell;
        this.baseCost = baseCost;
    }

    public Item(Integer id, String iconUrl, String description, String plainText, Integer totalCost, Integer sell, Integer baseCost, String name) {
        this.id = id;
        this.iconUrl = iconUrl;
        this.description = description;
        this.plainText = plainText;
        this.totalCost = totalCost;
        this.sell = sell;
        this.baseCost = baseCost;
        this.name = name;
    }

    public Item toUpdate(leagueoflegendsproject.Models.LoLApi.Items.Item item){
        this.baseCost = item.getGold().getBase();
        this.sell = item.getGold().getSell();
        this.totalCost = item.getGold().getTotal();
        this.plainText = item.getPlaintext();
        this.description = item.getDescription();
        this.iconUrl = item.getImage().getFull();
        this.name = item.getName();
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) && Objects.equals(iconUrl, item.iconUrl) && Objects.equals(description, item.description) && Objects.equals(plainText, item.plainText) && Objects.equals(totalCost, item.totalCost) && Objects.equals(sell, item.sell) && Objects.equals(baseCost, item.baseCost) && Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, iconUrl, description, plainText, totalCost, sell, baseCost, name);
    }

    @Override
    public String toString() {
        return "Item{}";
    }
}
