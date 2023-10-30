package leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.Matches.matchId;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Perks{

	@SerializedName("statPerks")
	private StatPerks statPerks;

	@SerializedName("styles")
	private List<StylesItem> styles;

	@Override
 	public String toString(){
		return 
			"Perks{" + 
			"statPerks = '" + statPerks + '\'' + 
			",styles = '" + styles + '\'' + 
			"}";
		}
}