package leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.Matches.puuid;

import java.util.List;
import java.util.Objects;

import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("Matches")
	private List<String> matches;

	public void setMatches(List<String> matches){
		this.matches = matches;
	}

	public List<String> getMatches(){
		return matches;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"matches = '" + matches + '\'' + 
			"}";
		}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Response response = (Response) o;
		return Objects.equals(matches, response.matches);
	}

	@Override
	public int hashCode() {
		return Objects.hash(matches);
	}
}