package leagueoflegendsproject.utils.scrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JsoupHttpConnectionWrapper {

    public Document getDocument(String url) throws IOException {
        return Jsoup.connect(url)
                .userAgent("PostmanRuntime/7.29.2")
                .get();
    }
}
