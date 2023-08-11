package leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.enums;

public enum DataDragonChampionKeyEnum {
    TYPE("type"),
    FORMAT("format"),
    VERSION("version"),
    DATA("data");

    private final String value;
    DataDragonChampionKeyEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
