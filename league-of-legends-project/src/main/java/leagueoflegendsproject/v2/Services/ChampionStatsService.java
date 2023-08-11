package leagueoflegendsproject.v2.Services;

import leagueoflegendsproject.v2.Models.Champion;
import leagueoflegendsproject.v2.Models.ChampionStats;
import leagueoflegendsproject.v2.Repositories.ChampionStatsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ChampionStatsService {

    private final ChampionStatsRepository championStatsRepository;

    public ChampionStats create(Double hp,
                                Double hpperlevel,
                                Double mp,
                                Double mpperlevel,
                                Double movespeed,
                                Double armor,
                                Double armorperlevel,
                                Double spellblock,
                                Double spellblockperlevel,
                                Double attackrange,
                                Double hpregen,
                                Double hpregenperlevel,
                                Double mpregen,
                                Double mpregenperlevel,
                                Double crit,
                                Double critperlevel,
                                Double attackdamage,
                                Double attackdamageperlevel,
                                Double attackspeedperlevel,
                                Double attackspeed,
                                Champion champion) {
        var stats = ChampionStats.builder()
                .champion(champion)
                .hp(hp)
                .hpperlevel(hpperlevel)
                .mp(mp)
                .mpperlevel(mpperlevel)
                .movespeed(movespeed)
                .armor(armor)
                .armorperlevel(armorperlevel)
                .spellblock(spellblock)
                .spellblockperlevel(spellblockperlevel)
                .attackdamage(attackdamage)
                .attackrange(attackrange)
                .hpregen(hpregen)
                .hpregenperlevel(hpregenperlevel)
                .mpregen(mpregen)
                .mpregenperlevel(mpregenperlevel)
                .crit(crit)
                .critperlevel(critperlevel)
                .attackdamageperlevel(attackdamageperlevel)
                .attackspeedperlevel(attackspeedperlevel)
                .attackspeed(attackspeed)
                .build();
        champion.setStats(stats);
        return stats;
    }

    public ChampionStats createAndSave(Double hp,
                                       Double hpperlevel,
                                       Double mp,
                                       Double mpperlevel,
                                       Double movespeed,
                                       Double armor,
                                       Double armorperlevel,
                                       Double spellblock,
                                       Double spellblockperlevel,
                                       Double attackrange,
                                       Double hpregen,
                                       Double hpregenperlevel,
                                       Double mpregen,
                                       Double mpregenperlevel,
                                       Double crit,
                                       Double critperlevel,
                                       Double attackdamage,
                                       Double attackdamageperlevel,
                                       Double attackspeedperlevel,
                                       Double attackspeed,
                                       Champion champion) {
        return championStatsRepository.save(create(hp,
                hpperlevel,
                mp,
                mpperlevel,
                movespeed,
                armor,
                armorperlevel,
                spellblock,
                spellblockperlevel,
                attackrange,
                hpregen,
                hpregenperlevel,
                mpregen,
                mpregenperlevel,
                crit,
                critperlevel,
                attackdamage,
                attackdamageperlevel,
                attackspeedperlevel,
                attackspeed,
                champion));
    }
}

