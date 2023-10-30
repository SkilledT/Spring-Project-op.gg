package leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.Perks;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SlotsItem{

	@SerializedName("runes")
	private List<RunesItem> runes;

	public void setRunes(List<RunesItem> runes){
		this.runes = runes;
	}

	public List<RunesItem> getRunes(){
		return runes;
	}
}