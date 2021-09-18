package leagueoflegendsproject.DTOs;

public class ItemMatchDto {
    private int itemId;
    private String itemUrl;

    public ItemMatchDto(int itemId, String itemUrl) {
        this.itemId = itemId;
        this.itemUrl = itemUrl;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }
}
