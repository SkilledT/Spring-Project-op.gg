package leagueoflegendsproject.Helpers.TestUtils;

import leagueoflegendsproject.Models.LoLApi.Items.Gold;
import leagueoflegendsproject.Models.LoLApi.Items.Image;
import leagueoflegendsproject.Models.LoLApi.Items.Item;

import java.util.ArrayList;

/*
    private Integer id;
	private String colloq;
	private Gold gold;
	private List<String> into;
	private Image image;
	private Maps maps;
	private Stats stats;
	private String name;
	private String description;
	private String plaintext;
	private List<String> tags;

 */
public class HttpItemBuilder {
    private Item item = new Item();

    public HttpItemBuilder(){
        Gold gold = new Gold(100, true, 200, 300);
        Image image = new Image();
        image.setFull("full.png");
        this.item.setGold(gold);
        this.item.setImage(image);
        this.item.setId(1);
        this.item.setColloq("colloq1");
        this.item.setInto(new ArrayList<>());
        this.item.setName("itemName1");
        this.item.setDescription("desc1");
        this.item.setPlaintext("plain1");
        this.item.setTags(new ArrayList<>());
    }

    public HttpItemBuilder withId(Integer id){
        this.item.setId(id);
        return this;
    }

    public HttpItemBuilder withColloq(String colloq){
        this.item.setColloq(colloq);
        return this;
    }

    public HttpItemBuilder withName(String name){
        this.item.setName(name);
        return this;
    }

    public HttpItemBuilder withDescription(String desc){
        this.item.setDescription(desc);
        return this;
    }

    public HttpItemBuilder withPlainText(String plainText){
        this.item.setPlaintext(plainText);
        return this;
    }

    public HttpItemBuilder withGold(Gold gold){
        this.item.setGold(gold);
        return this;
    }

    public HttpItemBuilder withImage(Image image){
        this.item.setImage(image);
        return this;
    }

    public Item build(){
        return this.item;
    }
}
