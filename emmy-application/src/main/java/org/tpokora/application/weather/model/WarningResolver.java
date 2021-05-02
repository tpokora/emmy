package org.tpokora.application.weather.model;

import org.tpokora.application.common.utils.IResolver;
import org.tpokora.domain.weather.common.WarningStrings;

public class WarningResolver implements IResolver<String, String> {

    public WarningResolver() {}

    public String resolve(String warningName) {
        if ("mroz".equalsIgnoreCase(warningName)) {
            return WarningStrings.FROST;
        }
        if ("upal".equalsIgnoreCase(warningName)) {
            return WarningStrings.HEAT;
        }
        if ("wiatr".equalsIgnoreCase(warningName)) {
            return WarningStrings.WIND;
        }
        if ("opad".equalsIgnoreCase(warningName)) {
            return WarningStrings.RAINFALL;
        }
        if ("burza".equalsIgnoreCase(warningName)) {
            return WarningStrings.STORM;
        }
        if ("traba".equalsIgnoreCase(warningName)) {
            return WarningStrings.WHIRLWIND;
        }
        return "";
    }
}
