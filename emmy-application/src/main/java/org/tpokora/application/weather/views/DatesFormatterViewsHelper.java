package org.tpokora.application.weather.views;

import org.tpokora.common.utils.DateUtils;

import java.time.LocalDateTime;

public class DatesFormatterViewsHelper {

    public String parseDate(LocalDateTime date) {
        return DateUtils.parseDateToString(date);
    }
}
