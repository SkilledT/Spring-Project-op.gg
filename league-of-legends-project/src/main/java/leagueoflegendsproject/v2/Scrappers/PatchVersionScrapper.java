package leagueoflegendsproject.v2.Scrappers;

import leagueoflegendsproject.utils.scrapper.JsoupHttpConnectionWrapper;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class PatchVersionScrapper {

    private final JsoupHttpConnectionWrapper jsoupHttpConnectionWrapper;

    public static final String URL = "https://support-leagueoflegends.riotgames.com/hc/en-us/articles/360018987893-Patch-Schedule-League-of-Legends";

    public List<LoLPatchVersion> scrapPatchVersions() {
        try {
            Document doc = jsoupHttpConnectionWrapper.getDocument(URL);
            Element table = doc.select("table").first();
            Element body = table.select("tbody").first();
            Elements rows = body.select("tr");
            return rows.stream().map(row -> {
                        Elements cols = row.select("td");
                        return new LoLPatchVersion()
                                .setDate(resolveDate(cols.get(1).text()))
                                .setVersion(cols.get(0).text());
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Unexpected error while processing Patch Versions");
            return Collections.emptyList();
        }
    }

    private Instant resolveDate(String dateString) {
        LocalDate localDate = LocalDate.of(getYear(dateString), getMonth(dateString), getDay(dateString));
        Instant instant = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        return instant;
    }

    private int getMonth(String date) {
        String month = getStringDatePart(date, DatePart.MONTH);
        return monthResolver(month);
    }

    private int getDay(String date) {
        String day = getStringDatePart(date, DatePart.DAY);
        return Integer.parseInt(day);
    }

    private int getYear(String date) {
        String year = getStringDatePart(date, DatePart.YEAR);
        return Integer.parseInt(year);
    }

    private int monthResolver(String month) {
        switch (month) {
            case "Jan":
                return 1;
            case "Feb":
                return 2;
            case "March":
                return 3;
            case "April":
                return 4;
            case "May":
                return 5;
            case "June":
                return 6;
            case "July":
                return 7;
            case "Aug":
                return 8;
            case "Sept":
                return 9;
            case "Oct":
                return 10;
            case "Nov":
                return 11;
            case "Dec":
                return 12;
        }
        return -1;
    }

    private String getStringDatePart(String date, DatePart datePart) {
        String[] dateParts = date.split(",");
        String[] monthDayParts = dateParts[1].trim().split("\\s");
        String month = monthDayParts[0].trim();
        String day = monthDayParts[1].trim();
        String year = dateParts[2].trim();

        switch (datePart) {
            case DAY:
                return day;
            case YEAR:
                return year;
            case MONTH:
                return month;
        }
        return null;
    }

    private enum DatePart {
        DAY, MONTH, YEAR
    }

    @Getter
    @Setter
    @Accessors(chain = true)
    public class LoLPatchVersion {
        private String version;
        private Instant date;
    }
}