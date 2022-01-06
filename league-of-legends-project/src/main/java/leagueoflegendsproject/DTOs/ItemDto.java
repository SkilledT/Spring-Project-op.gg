package leagueoflegendsproject.DTOs;

import leagueoflegendsproject.Helpers.RiotLinksProvider;
import leagueoflegendsproject.Models.Database.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ItemDto {

    private String iconUrl;
    private String description;
    private String plainText;
    private Integer totalCost;
    private Integer sell;
    private Integer baseCost;
    private String name;

    public ItemDto(Item item) {
        this.iconUrl = RiotLinksProvider.ItemLinkProvider.getIconUrl(item);
        this.description = item.getDescription();
        this.plainText = item.getPlainText();
        this.totalCost = item.getTotalCost();
        this.sell = item.getSell();
        this.baseCost = item.getBaseCost();
        this.name = item.getName();
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlainText() {
        return plainText;
    }

    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }

    public Integer getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Integer totalCost) {
        this.totalCost = totalCost;
    }

    public Integer getSell() {
        return sell;
    }

    public void setSell(Integer sell) {
        this.sell = sell;
    }

    public Integer getBaseCost() {
        return baseCost;
    }

    public void setBaseCost(Integer baseCost) {
        this.baseCost = baseCost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemDto itemDto = (ItemDto) o;
        return Objects.equals(iconUrl, itemDto.iconUrl) && Objects.equals(description, itemDto.description) && Objects.equals(plainText, itemDto.plainText) && Objects.equals(totalCost, itemDto.totalCost) && Objects.equals(sell, itemDto.sell) && Objects.equals(baseCost, itemDto.baseCost) && Objects.equals(name, itemDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iconUrl, description, plainText, totalCost, sell, baseCost, name);
    }

    @Override
    public String toString() {
        return "ItemDto{" +
                "iconUrl='" + iconUrl + '\'' +
                ", description='" + description + '\'' +
                ", plainText='" + plainText + '\'' +
                ", totalCost=" + totalCost +
                ", sell=" + sell +
                ", baseCost=" + baseCost +
                ", name='" + name + '\'' +
                '}';
    }
}
