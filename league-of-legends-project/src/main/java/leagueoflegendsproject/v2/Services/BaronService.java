package leagueoflegendsproject.v2.Services;

import leagueoflegendsproject.v2.Models.Baron;
import leagueoflegendsproject.v2.Repositories.BaronRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BaronService {

    private final BaronRepository baronRepository;

    public Baron createBaron(int kills, boolean first) {
        return Baron.builder()
                .first(first)
                .kills(kills)
                .build();
    }

    public Baron saveBaron(int kills, boolean first) {
        var baron = createBaron(kills, first);
        return baronRepository.save(baron);
    }


}
