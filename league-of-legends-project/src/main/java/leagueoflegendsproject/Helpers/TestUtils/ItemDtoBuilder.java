package leagueoflegendsproject.Helpers.TestUtils;

import leagueoflegendsproject.DTOs.ItemDto;

public class ItemDtoBuilder {
    private ItemDto itemDto = new ItemDto();

    public ItemDtoBuilder withName(String name) {
        this.itemDto.setName(name);
        return this;
    }

    public ItemDtoBuilder withLongDesc(String longDesc) {
        this.itemDto.setLongDesc(longDesc);
        return this;
    }

    public ItemDtoBuilder withBasedCost(Integer basedCost) {
        this.itemDto.setBaseCost(basedCost);
        return this;
    }

    public ItemDtoBuilder withSellPrice(Integer sellPrice) {
        this.itemDto.setSell(sellPrice);
        return this;
    }

    public ItemDtoBuilder withTotalCost(Integer totalCost) {
        this.itemDto.setTotalCost(totalCost);
        return this;
    }

    public ItemDtoBuilder withIconUrl(String iconUrl) {
        this.itemDto.setIconUrl(iconUrl);
        return this;
    }

    public ItemDtoBuilder withPlainText(String plainText) {
        this.itemDto.setPlainText(plainText);
        return this;
    }

    public ItemDto build() {
        return this.itemDto;
    }
}
