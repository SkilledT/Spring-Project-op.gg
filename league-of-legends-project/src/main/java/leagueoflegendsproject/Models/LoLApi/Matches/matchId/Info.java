package leagueoflegendsproject.Models.LoLApi.Matches.matchId;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Info{

	@SerializedName("gameId")
	private long gameId;

	@SerializedName("gameType")
	private String gameType;

	@SerializedName("queueId")
	private int queueId;

	@SerializedName("gameDuration")
	private int gameDuration;

	@SerializedName("teams")
	private List<TeamsItem> teams;

	@SerializedName("gameStartTimestamp")
	private long gameStartTimestamp;

	@SerializedName("platformId")
	private String platformId;

	@SerializedName("gameCreation")
	private long gameCreation;

	@SerializedName("gameName")
	private String gameName;

	@SerializedName("tournamentCode")
	private String tournamentCode;

	@SerializedName("gameVersion")
	private String gameVersion;

	@SerializedName("mapId")
	private int mapId;

	@SerializedName("gameMode")
	private String gameMode;

	@SerializedName("participants")
	private List<ParticipantsItem> participants;

	public void setGameId(long gameId){
		this.gameId = gameId;
	}

	public long getGameId(){
		return gameId;
	}

	public void setGameType(String gameType){
		this.gameType = gameType;
	}

	public String getGameType(){
		return gameType;
	}

	public void setQueueId(int queueId){
		this.queueId = queueId;
	}

	public int getQueueId(){
		return queueId;
	}

	public void setGameDuration(int gameDuration){
		this.gameDuration = gameDuration;
	}

	public int getGameDuration(){
		return gameDuration;
	}

	public void setTeams(List<TeamsItem> teams){
		this.teams = teams;
	}

	public List<TeamsItem> getTeams(){
		return teams;
	}

	public void setGameStartTimestamp(long gameStartTimestamp){
		this.gameStartTimestamp = gameStartTimestamp;
	}

	public long getGameStartTimestamp(){
		return gameStartTimestamp;
	}

	public void setPlatformId(String platformId){
		this.platformId = platformId;
	}

	public String getPlatformId(){
		return platformId;
	}

	public void setGameCreation(long gameCreation){
		this.gameCreation = gameCreation;
	}

	public long getGameCreation(){
		return gameCreation;
	}

	public void setGameName(String gameName){
		this.gameName = gameName;
	}

	public String getGameName(){
		return gameName;
	}

	public void setTournamentCode(String tournamentCode){
		this.tournamentCode = tournamentCode;
	}

	public String getTournamentCode(){
		return tournamentCode;
	}

	public void setGameVersion(String gameVersion){
		this.gameVersion = gameVersion;
	}

	public String getGameVersion(){
		return gameVersion;
	}

	public void setMapId(int mapId){
		this.mapId = mapId;
	}

	public int getMapId(){
		return mapId;
	}

	public void setGameMode(String gameMode){
		this.gameMode = gameMode;
	}

	public String getGameMode(){
		return gameMode;
	}

	public void setParticipants(List<ParticipantsItem> participants){
		this.participants = participants;
	}

	public List<ParticipantsItem> getParticipants(){
		return participants;
	}

	@Override
 	public String toString(){
		return 
			"Info{" + 
			"gameId = '" + gameId + '\'' + 
			",gameType = '" + gameType + '\'' + 
			",queueId = '" + queueId + '\'' + 
			",gameDuration = '" + gameDuration + '\'' + 
			",teams = '" + teams + '\'' + 
			",gameStartTimestamp = '" + gameStartTimestamp + '\'' + 
			",platformId = '" + platformId + '\'' + 
			",gameCreation = '" + gameCreation + '\'' + 
			",gameName = '" + gameName + '\'' + 
			",tournamentCode = '" + tournamentCode + '\'' + 
			",gameVersion = '" + gameVersion + '\'' + 
			",mapId = '" + mapId + '\'' + 
			",gameMode = '" + gameMode + '\'' + 
			",participants = '" + participants + '\'' + 
			"}";
		}
}