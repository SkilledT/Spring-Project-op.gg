package leagueoflegendsproject.Models.LoLApi.Matches.matchId;

import com.google.gson.annotations.SerializedName;

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
}