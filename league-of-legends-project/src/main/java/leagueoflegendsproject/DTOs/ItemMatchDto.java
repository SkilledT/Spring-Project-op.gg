package leagueoflegendsproject.DTOs;

import leagueoflegendsproject.Helpers.RiotLinksProvider;

public class ItemMatchDto {
    private String itemId;
    private String itemUrl;

    public ItemMatchDto(String itemId) {
        this.itemId = itemId;
        this.itemUrl = RiotLinksProvider.ItemLinkProvider.getIconUrl(itemId);
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }
}
