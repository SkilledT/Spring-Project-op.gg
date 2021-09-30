package leagueoflegendsproject.Helpers;

import java.text.DecimalFormat;

public class NumericalHelpers {
    public static double doubleWithTwoPlaces(double d){
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(d));
    }
}
