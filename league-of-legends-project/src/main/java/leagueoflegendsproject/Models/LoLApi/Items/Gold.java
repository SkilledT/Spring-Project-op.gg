package leagueoflegendsproject.Models.LoLApi.Items;

import com.google.gson.annotations.SerializedName;

public class Gold{

	@SerializedName("total")
	private int total;

	@SerializedName("purchasable")
	private boolean purchasable;

	@SerializedName("sell")
	private int sell;

	@SerializedName("base")
	private int base;

	public Gold(int total, boolean purchasable, int sell, int base) {
		this.total = total;
		this.purchasable = purchasable;
		this.sell = sell;
		this.base = base;
	}

	public Gold() {
	}

	public int getTotal(){
		return total;
	}

	public boolean isPurchasable(){
		return purchasable;
	}

	public int getSell(){
		return sell;
	}

	public int getBase(){
		return base;
	}

	@Override
 	public String toString(){
		return 
			"Gold{" + 
			"total = '" + total + '\'' + 
			",purchasable = '" + purchasable + '\'' + 
			",sell = '" + sell + '\'' + 
			",base = '" + base + '\'' + 
			"}";
		}
}