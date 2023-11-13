package christmas;

import java.text.DecimalFormat;

public class DecimalUtil {
    public static String convertToFormat(Integer integer) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        return decimalFormat.format(integer);
    }
}
