package leagueoflegendsproject.Services.HttpServices;

import leagueoflegendsproject.Helpers.RiotHttpClient;
import leagueoflegendsproject.Models.Database.Champion.Champion;
import leagueoflegendsproject.Models.LoLApi.Champions.ChampionItem;
import leagueoflegendsproject.Repositories.*;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class HttpChampionService {

    private final RiotHttpClient riotHttpClient;

    public HttpChampionService(RiotHttpClient riotHttpClient) {
        this.riotHttpClient = riotHttpClient;
    }

    public List<ChampionItem> getChampionList() {
        if (riotHttpClient.getChampions().isSuccess())
            return riotHttpClient.getChampions().getResponse();
        return Collections.emptyList();
    }
}
