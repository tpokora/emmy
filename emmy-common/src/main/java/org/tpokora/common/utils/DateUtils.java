package org.tpokora.common.utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DateUtils {

    public final static String DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    public final static String DATE = "yyyy-MM-dd";

    private DateUtils() {}

    public static String parseDateToString(LocalDateTime localDateTime, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(dateTimeFormatter);
    }

    public static String parseDateToString(LocalDateTime localDateTime) {
        return parseDateToString(localDateTime, DATE_TIME);
    }

    public static LocalDateTime parseStringToDateTime(String dateString) {
        Objects.requireNonNull(dateString, "dateString String can't be null!");
        return parseStringToDateTime(dateString, DATE_TIME);
    }

    public static LocalDateTime parseStringToDateTime(String dateString, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(dateString, formatter);
    }

    public static LocalDateTime getCurrentLocalDateTime() {
        LocalDateTime now = LocalDateTime.now();
        now = now.minusNanos(now.getNano());
        return now;
    }

    public static long getMinuteDifference(LocalDateTime firstDate, LocalDateTime secondDate) {
        Objects.requireNonNull(firstDate, "first LocalDateTime can't be null!");
        Objects.requireNonNull(secondDate, "second LocalDateTime can't be null!");;
        return Duration.between(secondDate, firstDate).getSeconds() / 60;
    }


}
