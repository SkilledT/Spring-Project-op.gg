package leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.Matches.matchId;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Inhibitor{

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
			"Inhibitor{" + 
			"kills = '" + kills + '\'' + 
			",first = '" + first + '\'' + 
			"}";
		}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Inhibitor inhibitor = (Inhibitor) o;
		return kills == inhibitor.kills &&
				first == inhibitor.first;
	}

	@Override
	public int hashCode() {
		return Objects.hash(kills, first);
	}
}