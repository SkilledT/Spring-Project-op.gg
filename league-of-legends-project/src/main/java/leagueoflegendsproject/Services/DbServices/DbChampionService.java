package leagueoflegendsproject.Services.DbServices;

import leagueoflegendsproject.Models.Database.Champion.*;
import leagueoflegendsproject.Models.Database.TemporaryTables.ChampionWithWinRatioEntity;
import leagueoflegendsproject.Models.LoLApi.Champions.ChampionItem;
import leagueoflegendsproject.Repositories.*;
import leagueoflegendsproject.Repositories.Interfaces.ChampionRepositoryCustom;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

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
    private final ChampionRepositoryCustom championRepositoryCustom;

    public DbChampionService(ChampionRepository championRepository,
                             ChampionTagRepository championTagRepository,
                             ImageRepository imageRepository,
                             InfoRepository infoRepository,
                             ChampionRepositoryCustom championRepositoryCustom,
                             StatsRepository statsRepository,
                             TagRepository tagRepository) {
        this.championRepository = championRepository;
        this.championTagRepository = championTagRepository;
        this.imageRepository = imageRepository;
        this.infoRepository = infoRepository;
        this.statsRepository = statsRepository;
        this.tagRepository = tagRepository;
        this.championRepositoryCustom = championRepositoryCustom;
    }

    public Champion saveChampion(ChampionItem championItem){
        if (championRepository.existsById(Integer.parseInt(championItem.getKey())))
            return championRepository.findById(Integer.parseInt(championItem.getKey())).orElseThrow();

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

    private Set<Tag> fetchOrCreateTagList(ChampionItem championItem){
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

    private Stats createAndSaveStats(ChampionItem championItem){
        Stats stats = new Stats(championItem);
        return statsRepository.save(stats);
    }

    private Info createAndSaveInfo(ChampionItem championItem){
        Info info = new Info(championItem);
        return infoRepository.save(info);
    }

    private Image createAndSaveImage(ChampionItem championItem){
        Image image = new Image(championItem);
        return imageRepository.save(image);
    }

    public List<ChampionWithWinRatioEntity> getChampionWithWinRatioEntity(String championName, Integer minimumMatches){
        return championRepositoryCustom.getChampionWithWinRatioEntity(championName, minimumMatches);
    }
}
