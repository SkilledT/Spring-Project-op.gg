package leagueoflegendsproject.v2.Services;

import leagueoflegendsproject.v2.Models.RiftHerald;
import leagueoflegendsproject.v2.Repositories.RiftHeraldRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RiftHeraldService {

    private final RiftHeraldRepository riftHeraldRepository;

    public RiftHerald createRiftHerald(int kills, boolean first) {
        return RiftHerald.builder()
                .first(first)
                .kills(kills)
                .build();
    }

    public RiftHerald saveRiftHerald(int kills, boolean first) {
        var tower = createRiftHerald(kills, first);
        return riftHeraldRepository.save(tower);
    }
}
