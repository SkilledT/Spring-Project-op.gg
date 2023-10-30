package leagueoflegendsproject.Helpers;

import leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.Matches.matchId.Match;

public class MatchUtils {

    public static final int RANKED_5X5_SOLO = 420;

    public static boolean isMatchSoloQ(Match match){
        var result = match.getInfo().getQueueId() == RANKED_5X5_SOLO;
        return result;
    }

}
