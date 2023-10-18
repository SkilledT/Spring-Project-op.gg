package leagueoflegendsproject.v2.Services;

import leagueoflegendsproject.v2.Models.Ban;
import leagueoflegendsproject.v2.Models.ChampionSnapshot;
import leagueoflegendsproject.v2.Models.MatchParticipant;
import leagueoflegendsproject.v2.Repositories.BanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BanService {
    private final BanRepository banRepository;

    public Ban createBan(int pickTurn, MatchParticipant matchParticipant, ChampionSnapshot championSnapshot) {
        return Ban.builder()
                .championSnapshot(championSnapshot)
                .matchParticipant(matchParticipant)
                .pickTurn(pickTurn)
                .build();
    }

    public Ban saveBan(int pickTurn, MatchParticipant matchParticipant, ChampionSnapshot championSnapshot) {
        return banRepository.save(createBan(pickTurn, matchParticipant, championSnapshot));
    }
}
