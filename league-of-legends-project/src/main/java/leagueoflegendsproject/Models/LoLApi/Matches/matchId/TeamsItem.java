package leagueoflegendsproject.Models.LoLApi.Matches.matchId;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class TeamsItem{

	@SerializedName("teamId")
	private int teamId;

	@SerializedName("bans")
	private List<BansItem> bans;

	@SerializedName("objectives")
	private Objectives objectives;

	@SerializedName("win")
	private boolean win;

	public void setTeamId(int teamId){
		this.teamId = teamId;
	}

	public int getTeamId(){
		return teamId;
	}

	public void setBans(List<BansItem> bans){
		this.bans = bans;
	}

	public List<BansItem> getBans(){
		return bans;
	}

	public void setObjectives(Objectives objectives){
		this.objectives = objectives;
	}

	public Objectives getObjectives(){
		return objectives;
	}

	public void setWin(boolean win){
		this.win = win;
	}

	public boolean isWin(){
		return win;
	}

	@Override
 	public String toString(){
		return 
			"TeamsItem{" + 
			"teamId = '" + teamId + '\'' + 
			",bans = '" + bans + '\'' + 
			",objectives = '" + objectives + '\'' + 
			",win = '" + win + '\'' + 
			"}";
		}
}