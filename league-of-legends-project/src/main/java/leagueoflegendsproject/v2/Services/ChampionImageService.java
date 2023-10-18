package leagueoflegendsproject.v2.Services;

import leagueoflegendsproject.v2.Models.ChampionSnapshot;
import leagueoflegendsproject.v2.Models.ChampionImage;
import leagueoflegendsproject.v2.Repositories.ChampionImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChampionImageService {

    private final ChampionImageRepository championImageRepository;

    public ChampionImage create(String full, String sprite, String group, Integer x, Integer y, Integer w, Integer h, ChampionSnapshot championSnapshot) {
        var image = ChampionImage.builder()
                .fullName(full)
                .sprite(sprite)
                .groupName(group)
                .x(x)
                .y(y)
                .w(w)
                .h(h)
                .championSnapshot(championSnapshot)
                .build();
        championSnapshot.setImage(image);
        return image;
    }

    public ChampionImage createAndSave(String full, String sprite, String group, Integer x, Integer y, Integer w, Integer h, ChampionSnapshot championSnapshot) {
        return championImageRepository.save(create(full, sprite, group, x, y, w, h, championSnapshot));
    }
}
