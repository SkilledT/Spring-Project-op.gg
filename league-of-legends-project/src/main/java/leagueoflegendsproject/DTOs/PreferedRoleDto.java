package leagueoflegendsproject.DTOs;

import java.util.Objects;

public class PreferedRoleDto {
    private double pickRatio;
    private double winRatio;
    private String name;
    private String iconUrl;

    public PreferedRoleDto(double pickRatio, double winRatio, String name) {
        this.pickRatio = pickRatio;
        this.winRatio = winRatio;
        this.name = name;
        this.iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-fe-lol-clash/global/default/assets/images/position-selector/positions/icon-position-"+name.toLowerCase()+".png";
    }

    public PreferedRoleDto() {
    }

    public double getPickRatio() {
        return pickRatio;
    }

    public void setPickRatio(double pickRatio) {
        this.pickRatio = pickRatio;
    }

    public double getWinRatio() {
        return winRatio;
    }

    public void setWinRatio(double winRatio) {
        this.winRatio = winRatio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl() {
        this.iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-fe-lol-clash/global/default/assets/images/position-selector/positions/icon-position-"+name.toLowerCase()+".png";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PreferedRoleDto that = (PreferedRoleDto) o;
        return Double.compare(that.pickRatio, pickRatio) == 0 && Double.compare(that.winRatio, winRatio) == 0 && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pickRatio, winRatio, name);
    }
}
