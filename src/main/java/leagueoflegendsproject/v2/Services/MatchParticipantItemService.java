package leagueoflegendsproject.v2.Services;

import leagueoflegendsproject.v2.Models.Item;
import leagueoflegendsproject.v2.Models.MatchParticipant;
import leagueoflegendsproject.v2.Models.MatchParticipantItem;
import leagueoflegendsproject.v2.Repositories.MatchParticipantItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MatchParticipantItemService {

    private final MatchParticipantItemRepository matchParticipantItemRepository;

    public MatchParticipantItem createMatchParticipant(Integer position, Item item, MatchParticipant matchParticipant) {
        return new MatchParticipantItem()
                .setMatchParticipant(matchParticipant)
                .setItem(item)
                .setPosition(position);
    }

    public MatchParticipantItem createAndSaveMatchParticipant(Integer position, Item item, MatchParticipant matchParticipant) {
        var matchPartItem = createMatchParticipant(position, item, matchParticipant);
        var dbMatchParItem = matchParticipantItemRepository.save(matchPartItem);

        item.getMatchParticipantItems().add(dbMatchParItem);
        matchParticipant.getMatchParticipantItems().add(dbMatchParItem);

        return dbMatchParItem;
    }

}
