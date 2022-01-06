package leagueoflegendsproject.Helpers;

import java.io.IOException;

public class ChampionProvider implements Runnable {

    private final RiotHttpClient riotHttpClient;

    public ChampionProvider(RiotHttpClient riotHttpClient) {
        this.riotHttpClient = riotHttpClient;
    }

    /*
        Patches are released in every 2 weeks, so we have to check whether
        new champions has been released or stats has been changed
     */
    @Override
    public void run() {
        try {
            System.out.println(riotHttpClient.getChampions());
            Thread.sleep(1000 * 60 * 60 * 24 * 14);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
