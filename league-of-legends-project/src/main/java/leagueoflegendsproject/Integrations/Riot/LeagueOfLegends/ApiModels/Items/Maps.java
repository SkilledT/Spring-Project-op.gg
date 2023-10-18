package leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.Items;

import com.google.gson.annotations.SerializedName;

public class Maps{

	@SerializedName("1")
	private boolean jsonMember1;

	@SerializedName("12")
	private boolean jsonMember12;

	@SerializedName("8")
	private boolean jsonMember8;

	@SerializedName("10")
	private boolean jsonMember10;

	@SerializedName("11")
	private boolean jsonMember11;

	@SerializedName("22")
	private boolean jsonMember22;

	@SerializedName("21")
	private boolean jsonMember21;

	public boolean isJsonMember1(){
		return jsonMember1;
	}

	public boolean isJsonMember12(){
		return jsonMember12;
	}

	public boolean isJsonMember8(){
		return jsonMember8;
	}

	public boolean isJsonMember10(){
		return jsonMember10;
	}

	public boolean isJsonMember11(){
		return jsonMember11;
	}

	public boolean isJsonMember22(){
		return jsonMember22;
	}

	public boolean isJsonMember21(){
		return jsonMember21;
	}

	@Override
 	public String toString(){
		return 
			"Maps{" + 
			"1 = '" + jsonMember1 + '\'' + 
			",12 = '" + jsonMember12 + '\'' + 
			",8 = '" + jsonMember8 + '\'' + 
			",10 = '" + jsonMember10 + '\'' + 
			",11 = '" + jsonMember11 + '\'' + 
			",22 = '" + jsonMember22 + '\'' + 
			",21 = '" + jsonMember21 + '\'' + 
			"}";
		}
}