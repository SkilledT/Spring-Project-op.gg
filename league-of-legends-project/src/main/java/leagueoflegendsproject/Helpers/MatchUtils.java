package leagueoflegendsproject.Helpers;

import leagueoflegendsproject.Models.LoLApi.Matches.matchId.Match;

public class MatchUtils {

    public static final int RANKED_5X5_SOLO = 420;

    public static boolean checkIfMatchIsSoloQ(Match match){
        return match.getInfo().getQueueId() == RANKED_5X5_SOLO;
    }

}
