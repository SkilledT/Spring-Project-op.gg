package leagueoflegendsproject.Models.LoLApi.Matches.matchId;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class StylesItem{

	@SerializedName("selections")
	private List<SelectionsItem> selections;

	@SerializedName("description")
	private String description;

	@SerializedName("style")
	private int style;

	public void setSelections(List<SelectionsItem> selections){
		this.selections = selections;
	}

	public List<SelectionsItem> getSelections(){
		return selections;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setStyle(int style){
		this.style = style;
	}

	public int getStyle(){
		return style;
	}

	@Override
 	public String toString(){
		return 
			"StylesItem{" + 
			"selections = '" + selections + '\'' + 
			",description = '" + description + '\'' + 
			",style = '" + style + '\'' + 
			"}";
		}
}