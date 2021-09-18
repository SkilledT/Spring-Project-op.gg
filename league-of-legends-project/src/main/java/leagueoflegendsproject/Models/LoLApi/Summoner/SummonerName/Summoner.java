package leagueoflegendsproject.Models.LoLApi.Summoner.SummonerName;

import com.google.gson.annotations.SerializedName;

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

	public void setAccountId(String accountId){
		this.accountId = accountId;
	}

	public String getAccountId(){
		return accountId;
	}

	public void setProfileIconId(int profileIconId){
		this.profileIconId = profileIconId;
	}

	public int getProfileIconId(){
		return profileIconId;
	}

	public void setRevisionDate(long revisionDate){
		this.revisionDate = revisionDate;
	}

	public long getRevisionDate(){
		return revisionDate;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setPuuid(String puuid){
		this.puuid = puuid;
	}

	public String getPuuid(){
		return puuid;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setSummonerLevel(int summonerLevel){
		this.summonerLevel = summonerLevel;
	}

	public int getSummonerLevel(){
		return summonerLevel;
	}

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
}