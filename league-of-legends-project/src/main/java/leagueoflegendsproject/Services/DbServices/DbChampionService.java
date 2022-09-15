package leagueoflegendsproject.Services.DbServices;

import leagueoflegendsproject.DTOs.ChampionShortDto;
import leagueoflegendsproject.DTOs.ChampionStatsDTO;
import leagueoflegendsproject.Models.Database.Champion.*;
import leagueoflegendsproject.Models.Database.TemporaryTables.ChampionWithWinRatioEntity;
import leagueoflegendsproject.Models.LoLApi.Champions.ChampionItem;
import leagueoflegendsproject.Repositories.*;
import leagueoflegendsproject.Repositories.Interfaces.ChampionRepositoryCustom;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DbChampionService {
    private final ChampionRepository championRepository;
    private final ChampionTagRepository championTagRepository;
    private final ImageRepository imageRepository;
    private final InfoRepository infoRepository;
    private final StatsRepository statsRepository;
    private final TagRepository tagRepository;
    private final ChampionStatsRepository championStatsRepository;

    public DbChampionService(ChampionRepository championRepository,
                             ChampionTagRepository championTagRepository,
                             ImageRepository imageRepository,
                             InfoRepository infoRepository,
                             ChampionRepositoryCustom championRepositoryCustom,
                             StatsRepository statsRepository,
                             ChampionStatsRepository championStatsRepository,
                             TagRepository tagRepository) {
        this.championRepository = championRepository;
        this.championTagRepository = championTagRepository;
        this.imageRepository = imageRepository;
        this.infoRepository = infoRepository;
        this.statsRepository = statsRepository;
        this.tagRepository = tagRepository;
        this.championStatsRepository = championStatsRepository;
    }

    @Transactional
    public Champion saveChampion(ChampionItem championItem) {
        Stats stats = createAndSaveStats(championItem);
        Info info = createAndSaveInfo(championItem);
        Image image = createAndSaveImage(championItem);

        Champion champion = championRepository.findById(Integer.parseInt(championItem.getKey()))
                .orElseGet(() -> new Champion(championItem));
        champion.setStats(stats);
        champion.setInfo(info);
        champion.setImage(image);

        Set<ChampionTag> championTagSet = fetchOrCreateTagList(championItem).stream()
                .map(tag -> {
                    ChampionTag championTag = championTagRepository.findById(new ChampionTagKey(champion.getId(), tag.getId()))
                            .orElseGet(ChampionTag::new);
                    championTag.setChampion(champion);
                    championTag.setTag(tag);
                    championTagRepository.save(championTag);
                    return championTag;
                }).collect(Collectors.toSet());
        champion.setChampionTags(championTagSet);
        return champion;
    }

    private Set<Tag> fetchOrCreateTagList(ChampionItem championItem) {
        Set<Tag> tags = championItem.getTags().stream()
                .map(tagName -> tagRepository.findByName(tagName)
                        .orElseGet(() -> {
                            Tag tag = new Tag();
                            tag.setName(tagName);
                            return tag;
                        }))
                .collect(Collectors.toSet());
        return tags;
    }

    private Stats createAndSaveStats(ChampionItem championItem) {
        Stats stats = new Stats(championItem);
        return statsRepository.save(stats);
    }

    private Info createAndSaveInfo(ChampionItem championItem) {
        Info info = new Info(championItem);
        return infoRepository.save(info);
    }

    private Image createAndSaveImage(ChampionItem championItem) {
        Image image = new Image(championItem);
        return imageRepository.save(image);
    }

    public List<ChampionStatsDTO> getChampionsStrongAgainst(String championName) {
        return championStatsRepository.findTop3ChampionStatsByChampionNameOrderByWinRatioDesc(championName)
                .stream()
                .map(ChampionStatsDTO::new)
                .collect(Collectors.toList());
    }

    public List<ChampionStatsDTO> getChampionsWeakAgainst(String championName) {
        return championStatsRepository.findTop3ChampionStatsByChampionNameOrderByWinRatioAsc(championName)
                .stream()
                .map(ChampionStatsDTO::new)
                .collect(Collectors.toList());
    }

    public List<Champion> getAllChampions() {
        return championRepository.findAll();
    }

    @Cacheable(cacheNames = "Champions")
    public List<ChampionShortDto> getShortChampionDetails() {
        return getAllChampions().stream()
                .map(ChampionShortDto::new)
                .collect(Collectors.toList());
    }
}
