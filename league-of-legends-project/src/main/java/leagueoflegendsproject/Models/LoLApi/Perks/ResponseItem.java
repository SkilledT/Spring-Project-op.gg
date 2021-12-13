package leagueoflegendsproject.Models.LoLApi.Perks;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseItem{

	@SerializedName("slots")
	private List<SlotsItem> slots;

	@SerializedName("icon")
	private String icon;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("key")
	private String key;

	public void setSlots(List<SlotsItem> slots){
		this.slots = slots;
	}

	public List<SlotsItem> getSlots(){
		return slots;
	}

	public void setIcon(String icon){
		this.icon = icon;
	}

	public String getIcon(){
		return icon;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setKey(String key){
		this.key = key;
	}

	public String getKey(){
		return key;
	}
}