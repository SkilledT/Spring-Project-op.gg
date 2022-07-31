package leagueoflegendsproject.Helpers.TestUtils;

public class Constants {

    public static final String REMOVE_ALL_HTML_TAGS_REGEX = "<[^>]*>";

    public static class MatchParticipantConstants {
        public enum IndividualPosition {
            UTILITY("UTILITY"),
            MIDDLE("MIDDLE"),
            JUNGLE("JUNGLE"),
            Invalid("Invalid"),
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

        public enum RankedType {
            RANKED_SOLO_5x5, RANKED_SOLO_3x3
        }

        public enum RankedTier {
            IRON("Iron"),
            BRONZE("Bronze"),
            SILVER("Silver"),
            GOLD("Gold"),
            PLATINUM("Platinum"),
            DIAMOND("Diamond"),
            GRANDMASTER("Grandmaster"),
            MASTER("Master"),
            CHALLENGER("Challenger");


            private String value;

            RankedTier(final String value) {
                this.value = value;
            }

            public String getValue() {
                return this.value;
            }

            @Override
            public String toString() {
                return this.getValue();
            }
        }

        public enum RankedRank {
            I, II, III, IV
        }
    }

    public static class EntityConstants {
        public static class Procedures {
            public final static String FILL_CHAMPION_STATS = "fillChampionStats";
            public final static String UPDATE_CHAMPION_PERKS = "updateChampionPerks";
                public final static String UPDATE_MATCH_PARTICIPANT_AVERAGE_PERFORMANCE_AGGREGATES = "updateMatchParticipantAveragePerformanceAggregates";
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
