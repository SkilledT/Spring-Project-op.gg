package leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.Items;

import com.google.gson.annotations.SerializedName;

public class Image{

	@SerializedName("w")
	private int W;

	@SerializedName("sprite")
	private String sprite;

	@SerializedName("x")
	private int X;

	@SerializedName("h")
	private int H;

	@SerializedName("y")
	private int Y;

	@SerializedName("full")
	private String full;

	@SerializedName("group")
	private String group;

	public void setW(int w) {
		W = w;
	}

	public void setSprite(String sprite) {
		this.sprite = sprite;
	}

	public void setX(int x) {
		X = x;
	}

	public void setH(int h) {
		H = h;
	}

	public void setY(int y) {
		Y = y;
	}

	public void setFull(String full) {
		this.full = full;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public int getW(){
		return W;
	}

	public String getSprite(){
		return sprite;
	}

	public int getX(){
		return X;
	}

	public int getH(){
		return H;
	}

	public int getY(){
		return Y;
	}

	public String getFull(){
		return full;
	}

	public String getGroup(){
		return group;
	}

	@Override
 	public String toString(){
		return 
			"Image{" + 
			"w = '" + W + '\'' + 
			",sprite = '" + sprite + '\'' + 
			",x = '" + X + '\'' + 
			",h = '" + H + '\'' + 
			",y = '" + Y + '\'' + 
			",full = '" + full + '\'' + 
			",group = '" + group + '\'' + 
			"}";
		}
}