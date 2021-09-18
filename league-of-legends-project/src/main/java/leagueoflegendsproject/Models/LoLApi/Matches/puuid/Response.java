package leagueoflegendsproject.Models.LoLApi.Matches.puuid;

import java.util.List;
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
}