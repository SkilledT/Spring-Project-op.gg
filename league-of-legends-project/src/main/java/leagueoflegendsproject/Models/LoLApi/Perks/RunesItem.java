package leagueoflegendsproject.Models.LoLApi.Perks;

import com.google.gson.annotations.SerializedName;

public class RunesItem{

	@SerializedName("icon")
	private String icon;

	@SerializedName("name")
	private String name;

	@SerializedName("shortDesc")
	private String shortDesc;

	@SerializedName("id")
	private int id;

	@SerializedName("key")
	private String key;

	@SerializedName("longDesc")
	private String longDesc;

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

	public void setShortDesc(String shortDesc){
		this.shortDesc = shortDesc;
	}

	public String getShortDesc(){
		return shortDesc;
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

	public void setLongDesc(String longDesc){
		this.longDesc = longDesc;
	}

	public String getLongDesc(){
		return longDesc;
	}
}