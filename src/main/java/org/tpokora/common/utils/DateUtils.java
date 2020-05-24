package org.tpokora.common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public final static String DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    private DateUtils() {}

    public static final String parseDateToString(LocalDateTime localDateTime, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(dateTimeFormatter);
    }

    public static final String parseDateToString(LocalDateTime localDateTime) {
        return parseDateToString(localDateTime, DATE_TIME);
    }


}
