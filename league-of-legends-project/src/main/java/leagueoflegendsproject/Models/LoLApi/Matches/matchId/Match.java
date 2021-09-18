package leagueoflegendsproject.Models.LoLApi.Matches.matchId;

import com.google.gson.annotations.SerializedName;

public class Match {

	@SerializedName("metadata")
	private Metadata metadata;

	@SerializedName("info")
	private Info info;

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
}