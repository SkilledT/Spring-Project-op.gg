package leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.Summoner.SummonerName;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Summoner {

	@SerializedName("accountId")
	private String accountId;

	@SerializedName("profileIconId")
	private int profileIconId;

	@SerializedName("revisionDate")
	private long revisionDate;

	@SerializedName("name")
	private String name;

	@SerializedName("puuid")
	private String puuid;

	@SerializedName("id")
	private String id;

	@SerializedName("summonerLevel")
	private int summonerLevel;

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"accountId = '" + accountId + '\'' + 
			",profileIconId = '" + profileIconId + '\'' + 
			",revisionDate = '" + revisionDate + '\'' + 
			",name = '" + name + '\'' + 
			",puuid = '" + puuid + '\'' + 
			",id = '" + id + '\'' + 
			",summonerLevel = '" + summonerLevel + '\'' + 
			"}";
		}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Summoner summoner = (Summoner) o;
		return profileIconId == summoner.profileIconId &&
				revisionDate == summoner.revisionDate &&
				summonerLevel == summoner.summonerLevel &&
				Objects.equals(accountId, summoner.accountId) &&
				Objects.equals(name, summoner.name) &&
				Objects.equals(puuid, summoner.puuid) &&
				Objects.equals(id, summoner.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountId, profileIconId, revisionDate, name, puuid, id, summonerLevel);
	}
}