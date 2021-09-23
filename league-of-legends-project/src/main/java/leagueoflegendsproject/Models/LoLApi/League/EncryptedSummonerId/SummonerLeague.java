package leagueoflegendsproject.Models.LoLApi.League.EncryptedSummonerId;

import java.util.List;
import java.util.Objects;

import com.google.gson.annotations.SerializedName;

public class SummonerLeague {

	@SerializedName("Response")
	private List<ResponseItem> response;

	public void setResponse(List<ResponseItem> response){
		this.response = response;
	}

	public List<ResponseItem> getResponse(){
		return response;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"response = '" + response + '\'' + 
			"}";
		}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SummonerLeague that = (SummonerLeague) o;
		return Objects.equals(response, that.response);
	}

	@Override
	public int hashCode() {
		return Objects.hash(response);
	}
}