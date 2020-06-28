package org.tpokora.weather.services.storms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.tpokora.weather.properties.StormProperties;
import org.tpokora.weather.dao.WarningDaoService;
import org.tpokora.weather.model.Coordinates;
import org.tpokora.weather.model.Period;
import org.tpokora.weather.model.Warning;
import org.tpokora.weather.model.WarningResolver;
import org.tpokora.weather.utils.StormsTestsHelper;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class FindWarningServiceTests extends StormServicesTests {

    public static final LocalDateTime TODAY = LocalDateTime.now();
    public static final int LEVEL = 1;
    public static final LocalDateTime TOMORROW = LocalDateTime.now().plusDays(LEVEL);
    public static final Period PERIOD = new Period(TODAY, TOMORROW);
    private static final WarningResolver warningResolver = new WarningResolver();

    @Mock
    private WarningDaoService warningDaoService;

    @InjectMocks
    private FindWarningService findWarningService;

    @Test
    public void testFindWarning() throws SOAPException {
        List<Warning> expectedWarnings = fillWarnings();
        SOAPMessage response = generateWarningsResponse(expectedWarnings);
        Coordinates coordinates = new Coordinates(11.11, 22.22);
        Mockito.when(stormProperties.getValue(StormProperties.KEY)).thenReturn(STORM_TEST_KEY);
        Mockito.when(soapService.sendSOAPMessage(any(), anyString())).thenReturn(response);
        List<Warning> warnings = findWarningService.findWarnings(coordinates);
        Assertions.assertFalse(warnings.isEmpty());
        Assertions.assertEquals(expectedWarnings.size(), warnings.size());
        for (int i = 0; i < warnings.size(); i++) {
            Warning currentWarning = warnings.get(i);
            Warning expectedWarning = expectedWarnings.get(i);
            Assertions.assertEquals(currentWarning.getName(), warningResolver.resolve(expectedWarning.getName()));
            Assertions.assertEquals(currentWarning.getLevel(), expectedWarning.getLevel());
            Assertions.assertEquals(currentWarning.getPeriod().toString(), expectedWarning.getPeriod().toString());
        }
    }

    private List<Warning> fillWarnings() {
        List<Warning> warnings = new ArrayList<>();
        warnings.add(StormsTestsHelper.createWarning("mroz", LEVEL, PERIOD));
        warnings.add(StormsTestsHelper.createWarning("upal", LEVEL, PERIOD));
        warnings.add(StormsTestsHelper.createWarning("wiatr", LEVEL, PERIOD));
        warnings.add(StormsTestsHelper.createWarning("opad", LEVEL, PERIOD));
        warnings.add(StormsTestsHelper.createWarning("burza", LEVEL, PERIOD));
        warnings.add(StormsTestsHelper.createWarning("traba", LEVEL, PERIOD));
        return warnings;
    }

}
