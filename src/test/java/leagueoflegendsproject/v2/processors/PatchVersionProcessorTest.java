package leagueoflegendsproject.v2.processors;

import leagueoflegendsproject.utils.scrapper.JsoupHttpConnectionWrapper;
import leagueoflegendsproject.v2.Repositories.PatchVersionRepository;
import leagueoflegendsproject.v2.Scrappers.PatchVersionScrapper;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class PatchVersionProcessorTest {

    @Autowired
    private PatchVersionProcessor patchVersionProcessor;
    @Autowired
    private PatchVersionRepository patchVersionRepository;

    @Test
    void processPatchVersions() throws IOException {
        File resourceFile = new File(getClass().getClassLoader()
                .getResource("lolapiversion/supportPatchVersionResponse.html")
                .getFile());

        Document mockDocument = Jsoup.parse(resourceFile, "UTF-8");
        List<ImmutablePair<Timestamp, String>> expectedPatchValuePairs = getExpectedPatchValuePairs();

        JsoupHttpConnectionWrapper httpConnectionWrapper = mock(JsoupHttpConnectionWrapper.class);
        when(httpConnectionWrapper.getDocument(eq(PatchVersionScrapper.URL))).thenReturn(mockDocument);

        var beforeInsert = patchVersionRepository.findAll();
        patchVersionProcessor.processPatchVersions();
        var afterInsert = patchVersionRepository.findAll();

        Assertions.assertEquals(beforeInsert.size(), 0);
        Assertions.assertEquals(afterInsert.size(), 24);

        afterInsert.forEach(patch -> {
            var patchPair = new ImmutablePair<>(patch.getTimestamp(), patch.getVersion());
            Assertions.assertTrue(expectedPatchValuePairs.contains(patchPair));
        });
    }

    private List<ImmutablePair<Timestamp, String>> getExpectedPatchValuePairs() {
        return List.of(
                new ImmutablePair<>(Timestamp.valueOf(LocalDateTime.of(2023, 1, 11, 0, 0)), "13.1"),
                new ImmutablePair<>(Timestamp.valueOf(LocalDateTime.of(2023, 1, 25, 0, 0)), "13.2"),
                new ImmutablePair<>(Timestamp.valueOf(LocalDateTime.of(2023, 2, 8, 0, 0)), "13.3"),
                new ImmutablePair<>(Timestamp.valueOf(LocalDateTime.of(2023, 2, 23, 0, 0)), "13.4"),
                new ImmutablePair<>(Timestamp.valueOf(LocalDateTime.of(2023, 3, 8, 0, 0)), "13.5"),
                new ImmutablePair<>(Timestamp.valueOf(LocalDateTime.of(2023, 3, 22, 0, 0)), "13.6"),
                new ImmutablePair<>(Timestamp.valueOf(LocalDateTime.of(2023, 4, 5, 0, 0)), "13.7"),
                new ImmutablePair<>(Timestamp.valueOf(LocalDateTime.of(2023, 4, 19, 0, 0)), "13.8"),
                new ImmutablePair<>(Timestamp.valueOf(LocalDateTime.of(2023, 5, 3, 0, 0)), "13.9"),
                new ImmutablePair<>(Timestamp.valueOf(LocalDateTime.of(2023, 5, 17, 0, 0)), "13.10"),
                new ImmutablePair<>(Timestamp.valueOf(LocalDateTime.of(2023, 6, 1, 0, 0)), "13.11"),
                new ImmutablePair<>(Timestamp.valueOf(LocalDateTime.of(2023, 6, 14, 0, 0)), "13.12"),
                new ImmutablePair<>(Timestamp.valueOf(LocalDateTime.of(2023, 6, 28, 0, 0)), "13.13"),
                new ImmutablePair<>(Timestamp.valueOf(LocalDateTime.of(2023, 7, 19, 0, 0)), "13.14"),
                new ImmutablePair<>(Timestamp.valueOf(LocalDateTime.of(2023, 8, 2, 0, 0)), "13.15"),
                new ImmutablePair<>(Timestamp.valueOf(LocalDateTime.of(2023, 8, 16, 0, 0)), "13.16"),
                new ImmutablePair<>(Timestamp.valueOf(LocalDateTime.of(2023, 8, 30, 0, 0)), "13.17"),
                new ImmutablePair<>(Timestamp.valueOf(LocalDateTime.of(2023, 9, 13, 0, 0)), "13.18"),
                new ImmutablePair<>(Timestamp.valueOf(LocalDateTime.of(2023, 9, 27, 0, 0)), "13.19"),
                new ImmutablePair<>(Timestamp.valueOf(LocalDateTime.of(2023, 10, 11, 0, 0)), "13.20"),
                new ImmutablePair<>(Timestamp.valueOf(LocalDateTime.of(2023, 10, 25, 0, 0)), "13.21"),
                new ImmutablePair<>(Timestamp.valueOf(LocalDateTime.of(2023, 11, 8, 0, 0)), "13.22"),
                new ImmutablePair<>(Timestamp.valueOf(LocalDateTime.of(2023, 11, 21, 0, 0)), "13.23"),
                new ImmutablePair<>(Timestamp.valueOf(LocalDateTime.of(2023, 12, 6, 0, 0)), "13.24")
        );
    }
}