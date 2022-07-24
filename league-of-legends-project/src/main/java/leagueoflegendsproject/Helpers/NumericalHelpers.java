package leagueoflegendsproject.Helpers;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class NumericalHelpers {
    public static double doubleWithTwoPlaces(double d) {
        DecimalFormat df = new DecimalFormat("##.##");
        return Double.parseDouble(df.format(d).replace(",", "."));
    }

    public static Date parseUnixTimestampToDateTime(Long unixTimestamp) {
        return new Date(TimeUnit.MILLISECONDS.convert(unixTimestamp, TimeUnit.SECONDS));
    }

    public static double changeDoublePrecision(int precision, double value) {
        var result = Double.parseDouble(String.format("%.2f", value).replace(",", "."));
        return Double.isNaN(result) ? -1.0 : result;
    }

    public static class Standardization {
        public static double getZValue(double rawScore, double mean, double standardDeviation) {
            return (rawScore - mean) / standardDeviation;
        }
    }
}
