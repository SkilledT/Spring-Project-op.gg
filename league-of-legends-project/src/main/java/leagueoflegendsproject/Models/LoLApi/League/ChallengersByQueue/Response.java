package leagueoflegendsproject.Models.LoLApi.League.ChallengersByQueue;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("entries")
	private List<EntriesItem> entries;

	@SerializedName("tier")
	private String tier;

	@SerializedName("leagueId")
	private String leagueId;

	@SerializedName("name")
	private String name;

	@SerializedName("queue")
	private String queue;

	public List<EntriesItem> getEntries(){
		return entries;
	}

	public String getTier(){
		return tier;
	}

	public String getLeagueId(){
		return leagueId;
	}

	public String getName(){
		return name;
	}

	public String getQueue(){
		return queue;
	}
}