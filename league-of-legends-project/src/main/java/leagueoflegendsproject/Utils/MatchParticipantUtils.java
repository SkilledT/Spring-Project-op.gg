package leagueoflegendsproject.Utils;

import leagueoflegendsproject.Helpers.NumericalHelpers;
import leagueoflegendsproject.Models.Database.MatchParticipant;
import org.apache.commons.math.stat.descriptive.moment.StandardDeviation;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
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

    public static BigDecimal getStandardDeviation(List<MatchParticipant> matchParticipants, ToDoubleFunction<MatchParticipant> toDoubleFunction) {
        double[] values =  matchParticipants.stream().mapToDouble(toDoubleFunction).toArray();
        return BigDecimal.valueOf(NumericalHelpers.changeDoublePrecision(2, new StandardDeviation().evaluate(values)));
    }

    public static BigDecimal getAverageValueOfNaN(List<MatchParticipant> matchParticipants, ToDoubleFunction<MatchParticipant> toDoubleFunction) {
        return BigDecimal.valueOf(NumericalHelpers.changeDoublePrecision(2, matchParticipants.stream().mapToDouble(toDoubleFunction).average().orElse(0.0)));
    }

    public static BigDecimal getAverageValueOfNaN(List<MatchParticipant> matchParticipants, ToIntFunction<MatchParticipant> toIntFunction) {
        return BigDecimal.valueOf(NumericalHelpers.changeDoublePrecision(2, matchParticipants.stream().mapToInt(toIntFunction).average().orElse(0.0)));
    }
}
