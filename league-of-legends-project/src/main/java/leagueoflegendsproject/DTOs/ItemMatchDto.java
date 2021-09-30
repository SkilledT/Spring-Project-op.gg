package leagueoflegendsproject.DTOs;

public class ItemMatchDto {
    private int itemId;
    private String itemUrl;

    public ItemMatchDto(int itemId, String itemUrl) {
        this.itemId = itemId;
        this.itemUrl = itemUrl;
    }

    public ItemMatchDto(int itemId) {
        this.itemId = itemId;
        this.itemUrl = "http://ddragon.leagueoflegends.com/cdn/11.19.1/img/item/"+itemId+".png";
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
