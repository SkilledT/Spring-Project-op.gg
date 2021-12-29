package leagueoflegendsproject.Models.Database;


import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Item")
@AllArgsConstructor
public class Item {

    @Id
    @Column(name = "item_id")
    private Integer id;

    @Column(name = "icon_url")
    private String iconUrl;
    @Column(name = "description")
    private String description;
    @Column(name = "plain_text")
    private String plainText;
    @Column(name = "total_cost")
    private Integer totalCost;
    @Column(name = "sell")
    private Integer sell;
    @Column(name = "base_cost")
    private Integer baseCost;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "item")
    Set<ParticipantItems> participantItemsSet = new HashSet<>();

    public Item(Integer id){
        this.id = id;
        this.iconUrl = "https://ddragon.leagueoflegends.com/cdn/11.22.1/img/item/" + id + ".png";
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

    public Item() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Set<ParticipantItems> getParticipantItemsSet() {
        return participantItemsSet;
    }

    public void setParticipantItemsSet(Set<ParticipantItems> participantItemsSet) {
        this.participantItemsSet = participantItemsSet;
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
