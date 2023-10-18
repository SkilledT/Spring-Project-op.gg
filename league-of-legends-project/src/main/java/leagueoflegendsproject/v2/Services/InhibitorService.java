package leagueoflegendsproject.v2.Services;

import leagueoflegendsproject.v2.Models.Inhibitor;
import leagueoflegendsproject.v2.Repositories.InhibitorRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InhibitorService {

    private final InhibitorRepository inhibitorRepository;

    public Inhibitor createInhibitor(int kills, boolean first) {
        return Inhibitor.builder()
                .first(first)
                .kills(kills)
                .build();
    }

    public Inhibitor saveInhibitor(int kills, boolean first) {
        var inhibitor = createInhibitor(kills, first);
        return inhibitorRepository.save(inhibitor);
    }
}
