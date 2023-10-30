package leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.League.EncryptedSummonerId;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.beans.Transient;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
public class SummonerLeagueResponseItem {

	@SerializedName("wins")
	private int wins;

	@SerializedName("freshBlood")
	private boolean freshBlood;

	@SerializedName("summonerName")
	private String summonerName;

	@SerializedName("leaguePoints")
	private int leaguePoints;

	@SerializedName("losses")
	private int losses;

	@SerializedName("inactive")
	private boolean inactive;

	@SerializedName("tier")
	private String tier;

	@SerializedName("veteran")
	private boolean veteran;

	@SerializedName("leagueId")
	private String leagueId;

	@SerializedName("hotStreak")
	private boolean hotStreak;

	@SerializedName("queueType")
	private String queueType;

	@SerializedName("rank")
	private String rank;

	@SerializedName("summonerId")
	private String summonerId;

	public SummonerLeagueResponseItem(String summonerName, String leagueId, String queueType, String rank) {
		this.summonerName = summonerName;
		this.leagueId = leagueId;
		this.queueType = queueType;
		this.rank = rank;
	}

	@Override
 	public String toString(){
		return 
			"ResponseItem{" + 
			"wins = '" + wins + '\'' + 
			",freshBlood = '" + freshBlood + '\'' + 
			",summonerName = '" + summonerName + '\'' + 
			",leaguePoints = '" + leaguePoints + '\'' + 
			",losses = '" + losses + '\'' + 
			",inactive = '" + inactive + '\'' + 
			",tier = '" + tier + '\'' + 
			",veteran = '" + veteran + '\'' + 
			",leagueId = '" + leagueId + '\'' + 
			",hotStreak = '" + hotStreak + '\'' + 
			",queueType = '" + queueType + '\'' + 
			",rank = '" + rank + '\'' + 
			",summonerId = '" + summonerId + '\'' + 
			"}";
		}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SummonerLeagueResponseItem that = (SummonerLeagueResponseItem) o;
		return wins == that.wins &&
				freshBlood == that.freshBlood &&
				leaguePoints == that.leaguePoints &&
				losses == that.losses &&
				inactive == that.inactive &&
				veteran == that.veteran &&
				hotStreak == that.hotStreak &&
				Objects.equals(summonerName, that.summonerName) &&
				Objects.equals(tier, that.tier) &&
				Objects.equals(leagueId, that.leagueId) &&
				Objects.equals(queueType, that.queueType) &&
				Objects.equals(rank, that.rank) &&
				Objects.equals(summonerId, that.summonerId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(wins, freshBlood, summonerName, leaguePoints, losses, inactive, tier, veteran, leagueId, hotStreak, queueType, rank, summonerId);
	}

	public enum QueueTypeEnum {
		RANKED_SOLO_5x5, RANKED_SOLO_3x3
	}
}