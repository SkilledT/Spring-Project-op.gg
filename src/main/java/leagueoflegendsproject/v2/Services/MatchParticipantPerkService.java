package leagueoflegendsproject.v2.Services;

import leagueoflegendsproject.v2.Models.MatchParticipant;
import leagueoflegendsproject.v2.Models.MatchParticipantPerk;
import leagueoflegendsproject.v2.Models.Perk;
import leagueoflegendsproject.v2.Repositories.MatchParticipantPerkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatchParticipantPerkService {

    private final MatchParticipantPerkRepository matchParticipantPerkRepository;

    public MatchParticipantPerk createMatchParticipantPerk(Perk perk, MatchParticipant matchParticipant,
                                                           MatchParticipantPerk.StyleType styleType) {
        return new MatchParticipantPerk()
                .setMatchParticipant(matchParticipant)
                .setPerk(perk)
                .setStyleType(styleType);
    }

    public MatchParticipantPerk createAndSaveMatchParticipantPerk(Perk perk, MatchParticipant matchParticipant,
                                                                  MatchParticipantPerk.StyleType styleType) {
        var partPerk = createMatchParticipantPerk(perk, matchParticipant, styleType);
        var dbPartPerk = matchParticipantPerkRepository.save(partPerk);

        perk.getMatchParticipantPerks().add(dbPartPerk);
        matchParticipant.getMatchParticipantPerks().add(dbPartPerk);

        return dbPartPerk;
    }

    public MatchParticipantPerk.StyleType resolveType(String description) {
        switch (description) {
            case "primaryStyle":
                return MatchParticipantPerk.StyleType.PRIMARY;
            case "subStyle":
                return MatchParticipantPerk.StyleType.SUB_STYLE;
            default:
                return MatchParticipantPerk.StyleType.UNKNOWN;
        }
    }

}
