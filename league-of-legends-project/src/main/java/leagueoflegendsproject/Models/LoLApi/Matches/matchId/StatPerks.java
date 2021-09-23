package leagueoflegendsproject.Models.LoLApi.Matches.matchId;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		StatPerks statPerks = (StatPerks) o;
		return offense == statPerks.offense &&
				defense == statPerks.defense &&
				flex == statPerks.flex;
	}

	@Override
	public int hashCode() {
		return Objects.hash(offense, defense, flex);
	}
}