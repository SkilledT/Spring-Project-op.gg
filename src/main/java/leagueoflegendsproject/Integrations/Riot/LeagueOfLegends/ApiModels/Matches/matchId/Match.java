package leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.Matches.matchId;

import com.google.gson.annotations.SerializedName;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
public class Match {

	@SerializedName("metadata")
	private Metadata metadata;

	@SerializedName("info")
	private Info info;

	public Match(Info info) {
		this.info = info;
	}

    public void setMetadata(Metadata metadata){
		this.metadata = metadata;
	}

	public Metadata getMetadata(){
		return metadata;
	}

	public void setInfo(Info info){
		this.info = info;
	}

	public Info getInfo(){
		return info;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"metadata = '" + metadata + '\'' + 
			",info = '" + info + '\'' + 
			"}";
		}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Match match = (Match) o;
		return Objects.equals(metadata, match.metadata) &&
				Objects.equals(info, match.info);
	}

	@Override
	public int hashCode() {
		return Objects.hash(metadata, info);
	}
}