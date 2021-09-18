package leagueoflegendsproject.Models.LoLApi.Matches.matchId;

import com.google.gson.annotations.SerializedName;

public class StatPerks{

	@SerializedName("offense")
	private int offense;

	@SerializedName("defense")
	private int defense;

	@SerializedName("flex")
	private int flex;

	public void setOffense(int offense){
		this.offense = offense;
	}

	public int getOffense(){
		return offense;
	}

	public void setDefense(int defense){
		this.defense = defense;
	}

	public int getDefense(){
		return defense;
	}

	public void setFlex(int flex){
		this.flex = flex;
	}

	public int getFlex(){
		return flex;
	}

	@Override
 	public String toString(){
		return 
			"StatPerks{" + 
			"offense = '" + offense + '\'' + 
			",defense = '" + defense + '\'' + 
			",flex = '" + flex + '\'' + 
			"}";
		}
}