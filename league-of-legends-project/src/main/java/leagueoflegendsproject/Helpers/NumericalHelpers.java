package leagueoflegendsproject.Helpers;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class NumericalHelpers {
    public static double doubleWithTwoPlaces(double d){
        DecimalFormat df = new DecimalFormat("##.##");
        return Double.parseDouble(df.format(d).replace(",", "."));
    }

    public static Date parseUnixTimestampToDateTime(Long unixTimestamp){
        return new Date(TimeUnit.MILLISECONDS.convert(unixTimestamp, TimeUnit.SECONDS));
    }
}
