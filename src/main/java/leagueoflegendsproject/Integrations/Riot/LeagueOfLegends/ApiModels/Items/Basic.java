package leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.Items;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Basic{

	@SerializedName("consumed")
	private boolean consumed;

	@SerializedName("requiredChampion")
	private String requiredChampion;

	@SerializedName("specialRecipe")
	private int specialRecipe;

	@SerializedName("maps")
	private Maps maps;

	@SerializedName("consumeOnFull")
	private boolean consumeOnFull;

	@SerializedName("stacks")
	private int stacks;

	@SerializedName("description")
	private String description;

	@SerializedName("plaintext")
	private String plaintext;

	@SerializedName("rune")
	private Rune rune;

	@SerializedName("tags")
	private List<Object> tags;

	@SerializedName("gold")
	private Gold gold;

	@SerializedName("colloq")
	private String colloq;

	@SerializedName("hideFromAll")
	private boolean hideFromAll;

	@SerializedName("into")
	private List<Object> into;

	@SerializedName("depth")
	private int depth;

	@SerializedName("requiredAlly")
	private String requiredAlly;

	@SerializedName("stats")
	private Stats stats;

	@SerializedName("inStore")
	private boolean inStore;

	@SerializedName("name")
	private String name;

	@SerializedName("from")
	private List<Object> from;

	@SerializedName("group")
	private String group;

	public boolean isConsumed(){
		return consumed;
	}

	public String getRequiredChampion(){
		return requiredChampion;
	}

	public int getSpecialRecipe(){
		return specialRecipe;
	}

	public Maps getMaps(){
		return maps;
	}

	public boolean isConsumeOnFull(){
		return consumeOnFull;
	}

	public int getStacks(){
		return stacks;
	}

	public String getDescription(){
		return description;
	}

	public String getPlaintext(){
		return plaintext;
	}

	public Rune getRune(){
		return rune;
	}

	public List<Object> getTags(){
		return tags;
	}

	public Gold getGold(){
		return gold;
	}

	public String getColloq(){
		return colloq;
	}

	public boolean isHideFromAll(){
		return hideFromAll;
	}

	public List<Object> getInto(){
		return into;
	}

	public int getDepth(){
		return depth;
	}

	public String getRequiredAlly(){
		return requiredAlly;
	}

	public Stats getStats(){
		return stats;
	}

	public boolean isInStore(){
		return inStore;
	}

	public String getName(){
		return name;
	}

	public List<Object> getFrom(){
		return from;
	}

	public String getGroup(){
		return group;
	}

	@Override
 	public String toString(){
		return 
			"Basic{" + 
			"consumed = '" + consumed + '\'' + 
			",requiredChampion = '" + requiredChampion + '\'' + 
			",specialRecipe = '" + specialRecipe + '\'' + 
			",maps = '" + maps + '\'' + 
			",consumeOnFull = '" + consumeOnFull + '\'' + 
			",stacks = '" + stacks + '\'' + 
			",description = '" + description + '\'' + 
			",plaintext = '" + plaintext + '\'' + 
			",rune = '" + rune + '\'' + 
			",tags = '" + tags + '\'' + 
			",gold = '" + gold + '\'' + 
			",colloq = '" + colloq + '\'' + 
			",hideFromAll = '" + hideFromAll + '\'' + 
			",into = '" + into + '\'' + 
			",depth = '" + depth + '\'' + 
			",requiredAlly = '" + requiredAlly + '\'' + 
			",stats = '" + stats + '\'' + 
			",inStore = '" + inStore + '\'' + 
			",name = '" + name + '\'' + 
			",from = '" + from + '\'' + 
			",group = '" + group + '\'' + 
			"}";
		}
}