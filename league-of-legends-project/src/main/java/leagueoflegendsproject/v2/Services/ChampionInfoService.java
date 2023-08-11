package leagueoflegendsproject.v2.Services;

import leagueoflegendsproject.v2.Models.Champion;
import leagueoflegendsproject.v2.Models.ChampionInfo;
import leagueoflegendsproject.v2.Repositories.ChampionInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChampionInfoService {

    private final ChampionInfoRepository championInfoRepository;

    public ChampionInfo create(Integer attack, Integer defense, Integer magic, Integer difficulty, Champion champion) {
        var info = ChampionInfo.builder()
                .champion(champion)
                .attack(attack)
                .defense(defense)
                .magic(magic)
                .difficulty(difficulty)
                .build();
        champion.setInfo(info);
        return info;
    }

    public ChampionInfo createAndSave(Integer attack, Integer defense, Integer magic, Integer difficulty, Champion champion) {
        return championInfoRepository.save(create(attack, defense, magic, difficulty, champion));
    }
}
