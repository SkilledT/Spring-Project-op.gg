package leagueoflegendsproject.Helpers.TestUtils;

public class Constants {

    public static final String REMOVE_ALL_HTML_TAGS_REGEX = "<[^>]*>";

    public static class MatchParticipantConstnts {
        public enum IndividualPosition {
            UTILITY, MARKSMAN, FIGHTER, ASSASSIN
        }
    }

    public static class EntityConstants {
        public static class Procedures {
            public final static String FILL_CHAMPION_STATS = "fillChampionStats";
            public final static String UPDATE_CHAMPION_PERKS = "updateChampionPerks";
        }
    }

    public static class TimeConstants {
        public static final long SECOND_TO_MILLISECONDS = 1000;
        public static final long MINUTE_TO_MILLISECONDS = SECOND_TO_MILLISECONDS * 60;
        public static final long HOUR_TO_MILLISECONDS = MINUTE_TO_MILLISECONDS * 60;


        public static long secondsToMilliseconds(Integer seconds) {
            return 1000 * seconds;
        }

        public static long minutesToMilliseconds(Integer minutes) {
            return secondsToMilliseconds(60) * minutes;
        }

        public static long hoursToMilliseconds(Integer hours) {
            return minutesToMilliseconds(60) * hours;
        }
    }
}
