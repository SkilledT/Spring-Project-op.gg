package leagueoflegendsproject.Models.LoLApi.Matches.matchId;

import com.google.gson.annotations.SerializedName;

public class Baron{

	@SerializedName("kills")
	private int kills;

	@SerializedName("first")
	private boolean first;

	public void setKills(int kills){
		this.kills = kills;
	}

	public int getKills(){
		return kills;
	}

	public void setFirst(boolean first){
		this.first = first;
	}

	public boolean isFirst(){
		return first;
	}

	@Override
 	public String toString(){
		return 
			"Baron{" + 
			"kills = '" + kills + '\'' + 
			",first = '" + first + '\'' + 
			"}";
		}
}