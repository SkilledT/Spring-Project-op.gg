package leagueoflegendsproject.v2.Services;

import leagueoflegendsproject.v2.Models.ChampionSnapshot;
import leagueoflegendsproject.v2.Models.ChampionInfo;
import leagueoflegendsproject.v2.Repositories.ChampionInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChampionInfoService {

    private final ChampionInfoRepository championInfoRepository;

    public ChampionInfo create(Integer attack, Integer defense, Integer magic, Integer difficulty, ChampionSnapshot championSnapshot) {
        var info = ChampionInfo.builder()
                .championSnapshot(championSnapshot)
                .attack(attack)
                .defense(defense)
                .magic(magic)
                .difficulty(difficulty)
                .build();
        championSnapshot.setInfo(info);
        return info;
    }

    public ChampionInfo createAndSave(Integer attack, Integer defense, Integer magic, Integer difficulty, ChampionSnapshot championSnapshot) {
        return championInfoRepository.save(create(attack, defense, magic, difficulty, championSnapshot));
    }
}
