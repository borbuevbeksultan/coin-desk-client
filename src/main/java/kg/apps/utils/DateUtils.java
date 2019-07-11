package kg.apps.utils;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static String convertToUtcIsoString(ZonedDateTime zonedDateTime) {
        return zonedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
