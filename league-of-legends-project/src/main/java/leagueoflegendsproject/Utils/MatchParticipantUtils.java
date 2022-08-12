package leagueoflegendsproject.Utils;

import leagueoflegendsproject.Models.Database.Item;
import leagueoflegendsproject.Models.Database.MatchParticipant;
import leagueoflegendsproject.Models.LoLApi.Matches.matchId.ParticipantsItem;
import leagueoflegendsproject.Repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class MatchParticipantUtils {

    @Autowired
    private static ItemRepository itemRepository;

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

    public List<Item> extractItems(ParticipantsItem participant) {
        List<Integer> itemIds =
                List.of(participant.getItem0(), participant.getItem1(), participant.getItem2(), participant.getItem3(), participant.getItem4(), participant.getItem5());
        return itemIds.stream().map(id -> itemRepository.findById(id).orElse(new Item(id))).collect(Collectors.toList());
    }
}
