package leagueoflegendsproject.Helpers;

import leagueoflegendsproject.Models.Database.Item;
import leagueoflegendsproject.Models.Database.Perk;

import java.util.List;

public class RiotLinksProvider {

    private final static String RIOT_VERSION = "10.16.1";
    public final static String RIOT_CHAMPION_URL = "http://ddragon.leagueoflegends.com/cdn/" + RIOT_VERSION + "/data/en_US/champion.json";
    public final static String RIOT_CHAMPION_PERKS_URL = "http://ddragon.leagueoflegends.com/cdn/" + RIOT_VERSION + "/data/en_US/runesReforged.json";

    public static class MatchLinksProvider {
        public static String getMatchCollectionUrl(String puuid, Integer numberOfMatches) {
            return "https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/" + puuid + "/ids?start=0&count=" + numberOfMatches;
        }

        public static String getMatchDetailsUrl(String id) {
            return "https://europe.api.riotgames.com/lol/match/v5/matches/" + id;
        }
    }

    public static class SummonerLinksProvider {
        public static final String RIOT_CHALLENGERS_URL = "https://eun1.api.riotgames.com/lol/league/v4/challengerleagues/by-queue/RANKED_SOLO_5x5";
        private static final String RIOT_PROFILE_ICON_URL = "https://ddragon.leagueoflegends.com/cdn/" + RIOT_VERSION + "/img/profileicon/%s.png";

        public static String getSummonerByNicknameUrl(String nickname) {
            return "https://eun1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + nickname;
        }

        public static String getSummonerLeagueByNicknameUrl(String summonerEncryptedId) {
            return "https://eun1.api.riotgames.com/lol/league/v4/entries/by-summoner/" + summonerEncryptedId;
        }

        public static String getRiotProfileIconUrl(Integer iconId) {
            return String.format(RIOT_PROFILE_ICON_URL, iconId);
        }
    }

    public static class PerkLinksProvider {
        private static final String basicUrl = "https://ddragon.canisback.com/img/";

        public static String getPerkIconUrl(Perk perk) {
            return basicUrl + perk.getIcon();
        }
    }

    public static class ItemLinkProvider {
        private static final String basicUrl = "https://ddragon.leagueoflegends.com/cdn/" + RIOT_VERSION + "/img/item/";
        public final static String RIOT_ITEMS_URL = "http://ddragon.leagueoflegends.com/cdn/" + RIOT_VERSION + "/data/en_US/item.json";

        public static String getIconUrl(Item item) {
            return basicUrl + item.getIconUrl();
        }

        public static String getIconUrl(String iconId) {
            return basicUrl + iconId + ".png";
        }
    }

    public static class ChampionLinkProvider {
        public static final String basicUrl = "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/";
        public static final String basicUrlIcon = "http://ddragon.leagueoflegends.com/cdn/%s/img/champion/%s.png";

        public static String getSlashImg(String full) {
            full = full.replace(".", "_0.").replace("png", "jpg");
            return basicUrl + full;
        }

        public static String getIconImg(String championName) {
            return String.format(basicUrlIcon, RIOT_VERSION, championName);
        }
    }

}
