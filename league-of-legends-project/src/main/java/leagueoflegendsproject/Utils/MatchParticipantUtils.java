package leagueoflegendsproject.Utils;

import leagueoflegendsproject.Helpers.NumericalHelpers;
import leagueoflegendsproject.Models.Database.*;
import leagueoflegendsproject.Models.Database.Champion.Champion;
import leagueoflegendsproject.Models.LoLApi.Matches.matchId.ParticipantsItem;
import leagueoflegendsproject.Models.LoLApi.Matches.matchId.SelectionsItem;
import leagueoflegendsproject.Models.LoLApi.Matches.matchId.StylesItem;
import leagueoflegendsproject.Repositories.ItemRepository;
import org.apache.commons.math.stat.descriptive.moment.StandardDeviation;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

@Component
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

    public void setMatchParticipantItems(ParticipantsItem participant, MatchParticipant matchParticipant, Map<Integer, Item> availableItems) {
        List<Integer> itemIds = List.of(participant.getItem0(), participant.getItem1(), participant.getItem2(), participant.getItem3(), participant.getItem4(), participant.getItem5());
        for (int i = 0; i < itemIds.size(); i++) {
            var itemId = itemIds.get(i);
            if (availableItems.containsKey(itemId)) {
                Item item = availableItems.get(itemId);
                ParticipantItems participantItems = new ParticipantItems();
                participantItems.setItem(item);
                participantItems.setMatchParticipant(matchParticipant);
                participantItems.setPosition(i);
                matchParticipant.addParticipantItemChild(participantItems);
            }
        }
    }

    public void setMatchParticipantPerks(ParticipantsItem participant, MatchParticipant matchParticipant, Map<Integer, Perk> availablePerks) {
        for (StylesItem styleItem : participant.getPerks().getStyles()) {
            for (SelectionsItem selectionsItem : styleItem.getSelections()) {
                MatchParticipantPerk matchParticipantPerk = new MatchParticipantPerk();
                if (availablePerks.containsKey(selectionsItem.getPerk())) {
                    Perk perk = availablePerks.get(selectionsItem.getPerk());
                    matchParticipantPerk.setMatchParticipant(matchParticipant);
                    matchParticipantPerk.setPerk(perk);
                    matchParticipant.addMatchParticipantPerkChild(matchParticipantPerk);
                }
            }
        }
    }

    public MatchTeam setMatchTeam(Team team, leagueoflegendsproject.Models.Database.Match match) {
        MatchTeam matchTeam = new MatchTeam();
        matchTeam.setTeam(team);
        matchTeam.setMatch(match);
        return matchTeam;
    }

    public void setBan(Set<MatchTeam> matchTeamList, leagueoflegendsproject.Models.LoLApi.Matches.matchId.Match match, Map<Integer, Champion> availableChampions) {
        for (MatchTeam matchTeam : matchTeamList) {
            match.getInfo().getTeams().stream().filter(e -> e.getTeamId() == matchTeam.getTeam().getId()).forEach(teamsItem -> {
                teamsItem.getBans().stream().forEach(banItem -> {
                    int pickTurn = banItem.getPickTurn();
                    int championId = banItem.getChampionId();
                    if (availableChampions.containsKey(championId)) {
                        Champion champion = availableChampions.get(championId);
                        Ban ban = new Ban();
                        ban.setMatchTeam(matchTeam);
                        ban.setChampion(champion);
                        ban.setPickTurn(pickTurn);
                        matchTeam.addBanChild(ban);
                    }
                });
            });
        }
    }
}
