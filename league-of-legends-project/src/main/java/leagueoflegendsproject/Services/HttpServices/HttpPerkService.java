package leagueoflegendsproject.Services.HttpServices;

import leagueoflegendsproject.Helpers.HttpResponseWrapper;
import leagueoflegendsproject.Helpers.RiotHttpClient;
import leagueoflegendsproject.Helpers.RiotLinksProvider;
import leagueoflegendsproject.Models.Database.Perk;
import leagueoflegendsproject.Models.LoLApi.Perks.ResponseItem;
import leagueoflegendsproject.Services.DbServices.DbPerkService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class HttpPerkService {

    private final RiotHttpClient riotHttpClient;

    public HttpPerkService(RiotHttpClient riotHttpClient) {
        this.riotHttpClient = riotHttpClient;
    }

    public List<Perk> getPerks() {
        HttpResponseWrapper<ResponseItem[]> response = null;
        try {
            response = riotHttpClient.get(RiotLinksProvider.RIOT_CHAMPION_PERKS_URL, ResponseItem[].class);
            if (!response.isSuccess())
                throw new IllegalArgumentException("Unable to retrieve data from Riot API, message: " + response.getResponseMessage());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        ResponseItem[] perkResponse = response.getResponse();
        List<Perk> result = new ArrayList<>();
        int treeNumber = 0;
        for (ResponseItem responseItem : perkResponse) {
            var slots = responseItem.getSlots();
            for (int i = 0; i < slots.size(); i++) {
                var runes = slots.get(i).getRunes();
                for (leagueoflegendsproject.Models.LoLApi.Perks.RunesItem runesItem : runes) {
                    Integer slotNumber = i;
                    Perk perk = new Perk(runesItem.getId(), runesItem.getName(), runesItem.getIcon(), runesItem.getShortDesc(), runesItem.getLongDesc(), slotNumber, treeNumber);
                    result.add(perk);
                }
            }
            treeNumber++;
        }
        return result;
    }
}
