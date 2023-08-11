package leagueoflegendsproject.v2.Services;

import leagueoflegendsproject.v2.Models.Champion;
import leagueoflegendsproject.v2.Models.ChampionImage;
import leagueoflegendsproject.v2.Repositories.ChampionImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChampionImageService {

    private final ChampionImageRepository championImageRepository;

    public ChampionImage create(String full, String sprite, String group, Integer x, Integer y, Integer w, Integer h, Champion champion) {
        var image = ChampionImage.builder()
                .full(full)
                .sprite(sprite)
                .group(group)
                .x(x)
                .y(y)
                .w(w)
                .h(h)
                .champion(champion)
                .build();
        champion.setImage(image);
        return image;
    }

    public ChampionImage createAndSave(String full, String sprite, String group, Integer x, Integer y, Integer w, Integer h, Champion champion) {
        return championImageRepository.save(create(full, sprite, group, x, y, w, h, champion));
    }
}
