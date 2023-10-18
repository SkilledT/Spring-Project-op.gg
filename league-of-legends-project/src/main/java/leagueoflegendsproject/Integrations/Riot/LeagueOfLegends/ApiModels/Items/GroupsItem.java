package leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.Items;

import com.google.gson.annotations.SerializedName;

public class GroupsItem{

	@SerializedName("id")
	private String id;

	@SerializedName("MaxGroupOwnable")
	private String maxGroupOwnable;

	public String getId(){
		return id;
	}

	public String getMaxGroupOwnable(){
		return maxGroupOwnable;
	}

	@Override
 	public String toString(){
		return 
			"GroupsItem{" + 
			"id = '" + id + '\'' + 
			",maxGroupOwnable = '" + maxGroupOwnable + '\'' + 
			"}";
		}
}