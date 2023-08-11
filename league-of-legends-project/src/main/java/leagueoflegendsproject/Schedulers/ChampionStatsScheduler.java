package leagueoflegendsproject.Schedulers;

import leagueoflegendsproject.Models.Database.Champion.Champion;
import leagueoflegendsproject.Models.Database.MatchParticipant;
import leagueoflegendsproject.Models.Database.Team;
import leagueoflegendsproject.Repositories.ChampionRepository;
import leagueoflegendsproject.Repositories.MatchParticipantRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ChampionStatsScheduler {

    private MatchParticipantRepository matchParticipantRepository;

    public void update() {

        // TODO: Dodać paginacje
        List<MatchParticipant> participations = matchParticipantRepository.findAll();
        Map<String, List<ChampionStatsTemporary>> championChampionStatsTemporaryMap = new HashMap<>();


        for (MatchParticipant participant : participations) {
            String matchChampionName = participant.getChampion().getName();
            Boolean isWin = participant.getWin();
            Team team = participant.getTeam();

            // TODO: Metoda do filtrowania enemies
            List<Champion> enemyChampions = participant.getMatch().getMatchParticipantSet()
                    .stream()
                    .filter(e -> !Objects.equals(e.getTeam().getId(), team.getId()))
                    .map(MatchParticipant::getChampion)
                    .collect(Collectors.toList());

            // TODO: Compute if absent
            // TODO: Stworzyc metody do czesci wspolnej
            for (Champion enemyChampion : enemyChampions) {
                if (!championChampionStatsTemporaryMap.containsKey(matchChampionName)) {
                    ChampionStatsTemporary championStatsTemporary =
                            new ChampionStatsTemporary(
                                    matchChampionName,
                                    enemyChampion.getName(),
                                    isWin ? 1L : 0L,
                                    !isWin ? 1L : 0L);
                    championChampionStatsTemporaryMap.put(matchChampionName, List.of(championStatsTemporary));
                } else {
                    List<ChampionStatsTemporary> value = championChampionStatsTemporaryMap.get(matchChampionName);
                    boolean hasEnemyChampion = championChampionStatsTemporaryMap.get(matchChampionName)
                            .stream()
                            .anyMatch(e -> e.enemyChampionName.equals(enemyChampion.getName()));
                    if (!hasEnemyChampion) {
                        ChampionStatsTemporary championStatsTemporary =
                                new ChampionStatsTemporary(
                                        matchChampionName,
                                        enemyChampion.getName(),
                                        isWin ? 1L : 0L,
                                        !isWin ? 1L : 0L);
                        value.add(championStatsTemporary);
                    } else {
                        Optional<ChampionStatsTemporary> optionalChampionStatsTemporary = championChampionStatsTemporaryMap.get(matchChampionName)
                                .stream()
                                .filter(e -> e.enemyChampionName.equals(enemyChampion.getName()))
                                .findFirst();
                        if (optionalChampionStatsTemporary.isPresent()) {
                            ChampionStatsTemporary championStats = optionalChampionStatsTemporary.get();
                            championStats.setDefeats(championStats.getDefeats() + (!isWin ? 1L : 0L));
                            championStats.setVictiories(championStats.getDefeats() + (isWin ? 1L : 0L));
                        }
                    }
                }
            }
        }
    }

    @AllArgsConstructor
    @Getter
    @Setter
    class ChampionStatsTemporary {
        public String sourceChampionName;
        public String enemyChampionName;
        public Long victiories = 0L;
        public Long defeats = 0L;
    }
}
