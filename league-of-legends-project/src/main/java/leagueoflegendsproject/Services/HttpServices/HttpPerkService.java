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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HttpPerkService {

    private final RiotHttpClient riotHttpClient;
    private final DbPerkService dbPerkService;

    public HttpPerkService(RiotHttpClient riotHttpClient,
                           DbPerkService dbPerkService) {
        this.riotHttpClient = riotHttpClient;
        this.dbPerkService = dbPerkService;
    }

    public List<Perk> getPerks() throws Exception {
        HttpResponseWrapper<ResponseItem[]> response = riotHttpClient.get(RiotLinksProvider.RIOT_CHAMPION_PERKS_URL, ResponseItem[].class);
        ResponseItem[] perkResponse = response.getResponse();
        List<Perk> result = new ArrayList<>();
        for (ResponseItem responseItem : perkResponse) {
            var slots = responseItem.getSlots();
            for (int i = 0; i < slots.size(); i++) {
                var runes = slots.get(i).getRunes();
                for (leagueoflegendsproject.Models.LoLApi.Perks.RunesItem runesItem : runes) {
                    Perk perk = new Perk();
                    perk.setId(runesItem.getId());
                    perk.setIcon(runesItem.getIcon());
                    perk.setLongDesc(runesItem.getLongDesc());
                    perk.setShortDesc(runesItem.getShortDesc());
                    perk.setName(runesItem.getName());
                    perk.setSlotNumber(i);
                    result.add(perk);
                    dbPerkService.savePerk(perk);
                }
            }
        }
        return result;
    }
}
