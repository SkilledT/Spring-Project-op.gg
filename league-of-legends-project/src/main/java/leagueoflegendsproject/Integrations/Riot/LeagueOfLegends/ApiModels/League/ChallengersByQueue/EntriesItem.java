package leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.League.ChallengersByQueue;

import com.google.gson.annotations.SerializedName;

public class EntriesItem{

	@SerializedName("wins")
	private int wins;

	@SerializedName("freshBlood")
	private boolean freshBlood;

	@SerializedName("inactive")
	private boolean inactive;

	@SerializedName("veteran")
	private boolean veteran;

	@SerializedName("hotStreak")
	private boolean hotStreak;

	@SerializedName("summonerName")
	private String summonerName;

	@SerializedName("rank")
	private String rank;

	@SerializedName("summonerId")
	private String summonerId;

	@SerializedName("leaguePoints")
	private int leaguePoints;

	@SerializedName("losses")
	private int losses;

	public int getWins(){
		return wins;
	}

	public boolean isFreshBlood(){
		return freshBlood;
	}

	public boolean isInactive(){
		return inactive;
	}

	public boolean isVeteran(){
		return veteran;
	}

	public boolean isHotStreak(){
		return hotStreak;
	}

	public String getSummonerName(){
		return summonerName;
	}

	public String getRank(){
		return rank;
	}

	public String getSummonerId(){
		return summonerId;
	}

	public int getLeaguePoints(){
		return leaguePoints;
	}

	public int getLosses(){
		return losses;
	}
}