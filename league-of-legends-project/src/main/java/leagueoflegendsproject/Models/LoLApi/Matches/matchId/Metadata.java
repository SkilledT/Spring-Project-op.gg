package leagueoflegendsproject.Models.LoLApi.Matches.matchId;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Metadata{

	@SerializedName("dataVersion")
	private String dataVersion;

	@SerializedName("matchId")
	private String matchId;

	@SerializedName("participants")
	private List<String> participants;

	public void setDataVersion(String dataVersion){
		this.dataVersion = dataVersion;
	}

	public String getDataVersion(){
		return dataVersion;
	}

	public void setMatchId(String matchId){
		this.matchId = matchId;
	}

	public String getMatchId(){
		return matchId;
	}

	public void setParticipants(List<String> participants){
		this.participants = participants;
	}

	public List<String> getParticipants(){
		return participants;
	}

	@Override
 	public String toString(){
		return 
			"Metadata{" + 
			"dataVersion = '" + dataVersion + '\'' + 
			",matchId = '" + matchId + '\'' + 
			",participants = '" + participants + '\'' + 
			"}";
		}
}