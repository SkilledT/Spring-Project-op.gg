package leagueoflegendsproject.Models.LoLApi.Items;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data{

	@SerializedName("item")
	private List<Item> items;
}