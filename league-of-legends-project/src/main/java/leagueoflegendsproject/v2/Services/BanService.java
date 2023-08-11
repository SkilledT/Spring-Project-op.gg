package leagueoflegendsproject.v2.Services;

import leagueoflegendsproject.v2.Models.Ban;
import leagueoflegendsproject.v2.Models.Champion;
import leagueoflegendsproject.v2.Models.MatchParticipant;
import leagueoflegendsproject.v2.Repositories.BanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BanService {

    private final BanRepository banRepository;

    public Ban createBan(int pickTurn, MatchParticipant matchParticipant, Champion champion) {
        return Ban.builder()
                .champion(champion)
                .matchParticipant(matchParticipant)
                .pickTurn(pickTurn)
                .build();
    }

    public Ban saveBan(int pickTurn, MatchParticipant matchParticipant, Champion champion) {
        return banRepository.save(createBan(pickTurn, matchParticipant, champion));
    }
}
