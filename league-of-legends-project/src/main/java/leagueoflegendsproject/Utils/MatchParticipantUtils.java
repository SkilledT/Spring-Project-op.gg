package leagueoflegendsproject.Utils;

import leagueoflegendsproject.Models.Database.MatchParticipant;

import java.util.List;
import java.util.stream.Collectors;

public class MatchParticipantUtils {

    public static List<MatchParticipant> getRelatedMatchParticipants(MatchParticipant mp, boolean isAlly) {
        Integer teamId = mp.getTeam().getId();
        if (isAlly)
            return mp.getMatch().getMatchParticipantSet().stream().filter(e -> e.getTeam().getId().equals(teamId)).collect(Collectors.toList());
        else
            return mp.getMatch().getMatchParticipantSet().stream().filter(e -> !e.getTeam().getId().equals(teamId)).collect(Collectors.toList());
    }

    public static double getKillParticipation(MatchParticipant matchParticipant) {
        List<MatchParticipant> allies = getRelatedMatchParticipants(matchParticipant, true);
        double alliesKills = allies.stream().mapToDouble(MatchParticipant::getKills).sum();
        double pInKill = (matchParticipant.getKills() + matchParticipant.getAssists()) / alliesKills;
        return pInKill;
    }
}
