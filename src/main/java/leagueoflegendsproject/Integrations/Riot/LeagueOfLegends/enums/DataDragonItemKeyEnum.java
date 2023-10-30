package leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.enums;

public enum DataDragonItemKeyEnum {
    TYPE("type"),
    VERSION("version"),
    BASIC("basic"),
    DATA("data"),
    GROUPS("groups"),
    TREE("tree");

    private final String value;
    DataDragonItemKeyEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
