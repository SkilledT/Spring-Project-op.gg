package leagueoflegendsproject.Helpers.TestUtils;

public class Constants {

    public static final String REMOVE_ALL_HTML_TAGS_REGEX = "<[^>]*>";

    public static class MatchParticipantConstants {
        public enum IndividualPosition {
            UTILITY("UTILITY"),
            MIDDLE("MIDDLE"),
            JUNGLE("JUNGLE"),
            INVALID("Invalid"),
            TOP("TOP"),
            BOTTOM("BOTTOM");

            private String value;

            IndividualPosition(final String value) {
                this.value = value;
            }

            public String getValue() {
                return value;
            }

            @Override
            public String toString() {
                return this.getValue();
            }
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


        public static long secondsToMilliseconds(long seconds) {
            return 1000 * seconds;
        }

        public static long minutesToMilliseconds(long minutes) {
            return secondsToMilliseconds(60) * minutes;
        }

        public static long hoursToMilliseconds(long hours) {
            return minutesToMilliseconds(60) * hours;
        }
    }
}
