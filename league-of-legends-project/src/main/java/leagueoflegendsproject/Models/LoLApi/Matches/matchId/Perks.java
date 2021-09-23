package leagueoflegendsproject.Models.LoLApi.Matches.matchId;

import java.util.List;
import java.util.Objects;

import com.google.gson.annotations.SerializedName;

public class Perks{

	@SerializedName("statPerks")
	private StatPerks statPerks;

	@SerializedName("styles")
	private List<StylesItem> styles;

	public void setStatPerks(StatPerks statPerks){
		this.statPerks = statPerks;
	}

	public StatPerks getStatPerks(){
		return statPerks;
	}

	public void setStyles(List<StylesItem> styles){
		this.styles = styles;
	}

	public List<StylesItem> getStyles(){
		return styles;
	}

	@Override
 	public String toString(){
		return 
			"Perks{" + 
			"statPerks = '" + statPerks + '\'' + 
			",styles = '" + styles + '\'' + 
			"}";
		}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Perks perks = (Perks) o;
		return Objects.equals(statPerks, perks.statPerks) &&
				Objects.equals(styles, perks.styles);
	}

	@Override
	public int hashCode() {
		return Objects.hash(statPerks, styles);
	}
}