package org.tpokora.weather.model;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tpokora.domain.weather.common.WarningStrings;
import org.tpokora.services.weather.model.WarningResolver;

class WarningResolverTest {

    @Test
    void testResolve() {
        WarningResolver warningResolver = new WarningResolver();

        Assertions.assertEquals(WarningStrings.FROST, warningResolver.resolve("mroz"));
        Assertions.assertEquals(Strings.EMPTY, warningResolver.resolve("test"));
    }
}