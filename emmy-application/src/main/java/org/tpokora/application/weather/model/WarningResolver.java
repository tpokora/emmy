package org.tpokora.application.weather.model;

import org.tpokora.application.common.utils.IResolver;
import org.tpokora.domain.weather.common.WarningStrings;

public class WarningResolver implements IResolver<String, String> {

    public WarningResolver() {}

    public String resolve(String warningName) {
        if ("mroz".equals(warningName.toLowerCase())) {
            return WarningStrings.FROST;
        }
        if ("upal".equals(warningName.toLowerCase())) {
            return WarningStrings.HEAT;
        }
        if ("wiatr".equals(warningName.toLowerCase())) {
            return WarningStrings.WIND;
        }
        if ("opad".equals(warningName.toLowerCase())) {
            return WarningStrings.RAINFALL;
        }
        if ("burza".equals(warningName.toLowerCase())) {
            return WarningStrings.STORM;
        }
        if ("traba".equals(warningName.toLowerCase())) {
            return WarningStrings.WHIRLWIND;
        }
        return "";
    }
}
