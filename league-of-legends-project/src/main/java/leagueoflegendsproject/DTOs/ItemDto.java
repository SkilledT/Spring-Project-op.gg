package leagueoflegendsproject.DTOs;

import leagueoflegendsproject.Helpers.RiotLinksProvider;
import leagueoflegendsproject.Helpers.TestUtils.Constants;
import leagueoflegendsproject.Models.Database.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ItemDto {

    private String iconUrl;
    private String longDesc;
    private String plainText;
    private Integer totalCost;
    private Integer sell;
    private Integer baseCost;
    private String name;

    public ItemDto(Item item) {
        this.iconUrl = RiotLinksProvider.ItemLinkProvider.getIconUrl(item);
        this.longDesc = item.getDescription() != null ? item.getDescription().replaceAll(Constants.REMOVE_ALL_HTML_TAGS_REGEX, "") : null;
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

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
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
        return Objects.equals(iconUrl, itemDto.iconUrl) && Objects.equals(longDesc, itemDto.longDesc) && Objects.equals(plainText, itemDto.plainText) && Objects.equals(totalCost, itemDto.totalCost) && Objects.equals(sell, itemDto.sell) && Objects.equals(baseCost, itemDto.baseCost) && Objects.equals(name, itemDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iconUrl, longDesc, plainText, totalCost, sell, baseCost, name);
    }

    @Override
    public String toString() {
        return "ItemDto{" +
                "iconUrl='" + iconUrl + '\'' +
                ", description='" + longDesc + '\'' +
                ", plainText='" + plainText + '\'' +
                ", totalCost=" + totalCost +
                ", sell=" + sell +
                ", baseCost=" + baseCost +
                ", name='" + name + '\'' +
                '}';
    }
}
