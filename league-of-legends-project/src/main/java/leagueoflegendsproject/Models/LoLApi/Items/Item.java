package leagueoflegendsproject.Models.LoLApi.Items;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Item {

	private Integer id;

	@SerializedName("colloq")
	private String colloq;

	@SerializedName("gold")
	private Gold gold;

	@SerializedName("into")
	private List<String> into;

	@SerializedName("image")
	private Image image;

	@SerializedName("maps")
	private Maps maps;

	@SerializedName("stats")
	private Stats stats;

	@SerializedName("name")
	private String name;

	@SerializedName("description")
	private String description;

	@SerializedName("plaintext")
	private String plaintext;

	@SerializedName("tags")
	private List<String> tags;

	public String getColloq(){
		return colloq;
	}

	public Gold getGold(){
		return gold;
	}

	public List<String> getInto(){
		return into;
	}

	public Image getImage(){
		return image;
	}

	public Maps getMaps(){
		return maps;
	}

	public Stats getStats(){
		return stats;
	}

	public String getName(){
		return name;
	}

	public String getDescription(){
		return description;
	}

	public String getPlaintext(){
		return plaintext;
	}

	public List<String> getTags(){
		return tags;
	}

	public void setColloq(String colloq) {
		this.colloq = colloq;
	}

	public void setGold(Gold gold) {
		this.gold = gold;
	}

	public void setInto(List<String> into) {
		this.into = into;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void setMaps(Maps maps) {
		this.maps = maps;
	}

	public void setStats(Stats stats) {
		this.stats = stats;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPlaintext(String plaintext) {
		this.plaintext = plaintext;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
 	public String toString(){
		return 
			"JsonMember1001{" + 
			"colloq = '" + colloq + '\'' + 
			",gold = '" + gold + '\'' + 
			",into = '" + into + '\'' + 
			",image = '" + image + '\'' + 
			",maps = '" + maps + '\'' + 
			",stats = '" + stats + '\'' + 
			",name = '" + name + '\'' + 
			",description = '" + description + '\'' + 
			",plaintext = '" + plaintext + '\'' + 
			",tags = '" + tags + '\'' + 
			"}";
		}
}