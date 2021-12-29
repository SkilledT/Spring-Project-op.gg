package leagueoflegendsproject.Models.LoLApi.Items;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class TreeItem{

	@SerializedName("header")
	private String header;

	@SerializedName("tags")
	private List<String> tags;

	public String getHeader(){
		return header;
	}

	public List<String> getTags(){
		return tags;
	}

	@Override
 	public String toString(){
		return 
			"TreeItem{" + 
			"header = '" + header + '\'' + 
			",tags = '" + tags + '\'' + 
			"}";
		}
}