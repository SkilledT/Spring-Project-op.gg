package leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.Matches.matchId;

import java.util.List;
import java.util.Objects;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Metadata{

	@SerializedName("dataVersion")
	private String dataVersion;

	@SerializedName("matchId")
	private String matchId;

	@SerializedName("participants")
	private List<String> participants;

	@Override
 	public String toString(){
		return 
			"Metadata{" + 
			"dataVersion = '" + dataVersion + '\'' + 
			",matchId = '" + matchId + '\'' + 
			",participants = '" + participants + '\'' + 
			"}";
		}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Metadata metadata = (Metadata) o;
		return Objects.equals(dataVersion, metadata.dataVersion) &&
				Objects.equals(matchId, metadata.matchId) &&
				Objects.equals(participants, metadata.participants);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataVersion, matchId, participants);
	}
}