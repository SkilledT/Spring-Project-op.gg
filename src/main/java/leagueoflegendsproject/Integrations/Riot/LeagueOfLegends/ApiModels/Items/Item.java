package leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.Items;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Item {

	private Integer id;

	private String version;

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