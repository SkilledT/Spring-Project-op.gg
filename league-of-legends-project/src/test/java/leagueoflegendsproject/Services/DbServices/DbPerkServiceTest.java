package leagueoflegendsproject.Services.DbServices;

import leagueoflegendsproject.Helpers.TestUtils.PerkBuilder;
import leagueoflegendsproject.Models.Database.Perk;
import leagueoflegendsproject.Repositories.PerkRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
class DbPerkServiceTest {

    @Autowired
    private PerkRepository perkRepository;
    @Autowired
    private DbPerkService dbPerkService;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void savePerk() throws Exception {
        //given
        Perk expectedPerk = new PerkBuilder().build();
        //when
        Perk actualPerk = dbPerkService.savePerk(expectedPerk);
        //then
        assertEquals(expectedPerk, actualPerk);
    }
}