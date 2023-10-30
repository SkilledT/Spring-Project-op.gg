package leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.Matches.matchId;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class StylesItem{

	@SerializedName("selections")
	private List<SelectionsItem> selections;

	@SerializedName("description")
	private String description;

	@SerializedName("style")
	private int style;

	@Override
 	public String toString(){
		return 
			"StylesItem{" + 
			"selections = '" + selections + '\'' + 
			",description = '" + description + '\'' + 
			",style = '" + style + '\'' + 
			"}";
		}
}