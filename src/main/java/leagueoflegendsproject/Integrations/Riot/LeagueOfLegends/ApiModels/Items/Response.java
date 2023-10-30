package leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.Items;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("data")
	private Data data;

	@SerializedName("tree")
	private List<TreeItem> tree;

	@SerializedName("groups")
	private List<GroupsItem> groups;

	@SerializedName("type")
	private String type;

	@SerializedName("basic")
	private Basic basic;

	@SerializedName("version")
	private String version;

	public Data getData(){
		return data;
	}

	public List<TreeItem> getTree(){
		return tree;
	}

	public List<GroupsItem> getGroups(){
		return groups;
	}

	public String getType(){
		return type;
	}

	public Basic getBasic(){
		return basic;
	}

	public String getVersion(){
		return version;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"data = '" + data + '\'' + 
			",tree = '" + tree + '\'' + 
			",groups = '" + groups + '\'' + 
			",type = '" + type + '\'' + 
			",basic = '" + basic + '\'' + 
			",version = '" + version + '\'' + 
			"}";
		}
}