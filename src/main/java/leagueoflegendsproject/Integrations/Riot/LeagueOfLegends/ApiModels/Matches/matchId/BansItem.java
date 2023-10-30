package leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.Matches.matchId;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class BansItem{

	@SerializedName("championId")
	private int championId;

	@SerializedName("pickTurn")
	private int pickTurn;

	public void setChampionId(int championId){
		this.championId = championId;
	}

	public int getChampionId(){
		return championId;
	}

	public void setPickTurn(int pickTurn){
		this.pickTurn = pickTurn;
	}

	public int getPickTurn(){
		return pickTurn;
	}

	@Override
 	public String toString(){
		return 
			"BansItem{" + 
			"championId = '" + championId + '\'' + 
			",pickTurn = '" + pickTurn + '\'' + 
			"}";
		}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BansItem bansItem = (BansItem) o;
		return championId == bansItem.championId &&
				pickTurn == bansItem.pickTurn;
	}

	@Override
	public int hashCode() {
		return Objects.hash(championId, pickTurn);
	}
}