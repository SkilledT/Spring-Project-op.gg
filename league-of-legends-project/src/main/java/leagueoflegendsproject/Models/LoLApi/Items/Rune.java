package leagueoflegendsproject.Models.LoLApi.Items;

import com.google.gson.annotations.SerializedName;

public class Rune{

	@SerializedName("isrune")
	private boolean isrune;

	@SerializedName("tier")
	private int tier;

	@SerializedName("type")
	private String type;

	public boolean isIsrune(){
		return isrune;
	}

	public int getTier(){
		return tier;
	}

	public String getType(){
		return type;
	}

	@Override
 	public String toString(){
		return 
			"Rune{" + 
			"isrune = '" + isrune + '\'' + 
			",tier = '" + tier + '\'' + 
			",type = '" + type + '\'' + 
			"}";
		}
}