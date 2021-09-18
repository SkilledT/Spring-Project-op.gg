package leagueoflegendsproject.Models.LoLApi.Matches.matchId;

import com.google.gson.annotations.SerializedName;

public class Objectives{

	@SerializedName("baron")
	private Baron baron;

	@SerializedName("inhibitor")
	private Inhibitor inhibitor;

	@SerializedName("dragon")
	private Dragon dragon;

	@SerializedName("riftHerald")
	private RiftHerald riftHerald;

	@SerializedName("champion")
	private Champion champion;

	@SerializedName("tower")
	private Tower tower;

	public void setBaron(Baron baron){
		this.baron = baron;
	}

	public Baron getBaron(){
		return baron;
	}

	public void setInhibitor(Inhibitor inhibitor){
		this.inhibitor = inhibitor;
	}

	public Inhibitor getInhibitor(){
		return inhibitor;
	}

	public void setDragon(Dragon dragon){
		this.dragon = dragon;
	}

	public Dragon getDragon(){
		return dragon;
	}

	public void setRiftHerald(RiftHerald riftHerald){
		this.riftHerald = riftHerald;
	}

	public RiftHerald getRiftHerald(){
		return riftHerald;
	}

	public void setChampion(Champion champion){
		this.champion = champion;
	}

	public Champion getChampion(){
		return champion;
	}

	public void setTower(Tower tower){
		this.tower = tower;
	}

	public Tower getTower(){
		return tower;
	}

	@Override
 	public String toString(){
		return 
			"Objectives{" + 
			"baron = '" + baron + '\'' + 
			",inhibitor = '" + inhibitor + '\'' + 
			",dragon = '" + dragon + '\'' + 
			",riftHerald = '" + riftHerald + '\'' + 
			",champion = '" + champion + '\'' + 
			",tower = '" + tower + '\'' + 
			"}";
		}
}