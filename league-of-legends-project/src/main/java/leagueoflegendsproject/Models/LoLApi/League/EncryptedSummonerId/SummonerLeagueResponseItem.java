package leagueoflegendsproject.Models.LoLApi.League.EncryptedSummonerId;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

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

	public void setWins(int wins){
		this.wins = wins;
	}

	public int getWins(){
		return wins;
	}

	public void setFreshBlood(boolean freshBlood){
		this.freshBlood = freshBlood;
	}

	public boolean isFreshBlood(){
		return freshBlood;
	}

	public void setSummonerName(String summonerName){
		this.summonerName = summonerName;
	}

	public String getSummonerName(){
		return summonerName;
	}

	public void setLeaguePoints(int leaguePoints){
		this.leaguePoints = leaguePoints;
	}

	public int getLeaguePoints(){
		return leaguePoints;
	}

	public void setLosses(int losses){
		this.losses = losses;
	}

	public int getLosses(){
		return losses;
	}

	public void setInactive(boolean inactive){
		this.inactive = inactive;
	}

	public boolean isInactive(){
		return inactive;
	}

	public void setTier(String tier){
		this.tier = tier;
	}

	public String getTier(){
		return tier;
	}

	public void setVeteran(boolean veteran){
		this.veteran = veteran;
	}

	public boolean isVeteran(){
		return veteran;
	}

	public void setLeagueId(String leagueId){
		this.leagueId = leagueId;
	}

	public String getLeagueId(){
		return leagueId;
	}

	public void setHotStreak(boolean hotStreak){
		this.hotStreak = hotStreak;
	}

	public boolean isHotStreak(){
		return hotStreak;
	}

	public void setQueueType(String queueType){
		this.queueType = queueType;
	}

	public String getQueueType(){
		return queueType;
	}

	public void setRank(String rank){
		this.rank = rank;
	}

	public String getRank(){
		return rank;
	}

	public void setSummonerId(String summonerId){
		this.summonerId = summonerId;
	}

	public String getSummonerId(){
		return summonerId;
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
}