package leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.Matches.matchId;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class SelectionsItem {

	@SerializedName("perk")
	private int perk;

	@SerializedName("var3")
	private int var3;

	@SerializedName("var2")
	private int var2;

	@SerializedName("var1")
	private int var1;

	@Override
 	public String toString(){
		return 
			"SelectionsItem{" + 
			"perk = '" + perk + '\'' + 
			",var3 = '" + var3 + '\'' + 
			",var2 = '" + var2 + '\'' + 
			",var1 = '" + var1 + '\'' + 
			"}";
		}
}