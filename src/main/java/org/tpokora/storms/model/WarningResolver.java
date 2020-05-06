package org.tpokora.storms.model;

import org.tpokora.common.utils.Resolver;

public class WarningResolver implements Resolver<String, String> {

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
