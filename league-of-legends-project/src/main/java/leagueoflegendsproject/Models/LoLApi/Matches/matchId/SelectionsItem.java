package leagueoflegendsproject.Models.LoLApi.Matches.matchId;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class SelectionsItem{

	@SerializedName("perk")
	private int perk;

	@SerializedName("var3")
	private int var3;

	@SerializedName("var2")
	private int var2;

	@SerializedName("var1")
	private int var1;

	public void setPerk(int perk){
		this.perk = perk;
	}

	public int getPerk(){
		return perk;
	}

	public void setVar3(int var3){
		this.var3 = var3;
	}

	public int getVar3(){
		return var3;
	}

	public void setVar2(int var2){
		this.var2 = var2;
	}

	public int getVar2(){
		return var2;
	}

	public void setVar1(int var1){
		this.var1 = var1;
	}

	public int getVar1(){
		return var1;
	}

	@Override
 	public String toString(){
		return 
			"SelectionsItem{" + 
			"perk = '" + perk + '\'' + 
			",var3 = '" + var3 + '\'' + 
			",var2 = '" + var2 + '\'' + 
			",var1 = '" + var1 + '\'' + 
			"}";
		}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SelectionsItem that = (SelectionsItem) o;
		return perk == that.perk &&
				var3 == that.var3 &&
				var2 == that.var2 &&
				var1 == that.var1;
	}

	@Override
	public int hashCode() {
		return Objects.hash(perk, var3, var2, var1);
	}
}