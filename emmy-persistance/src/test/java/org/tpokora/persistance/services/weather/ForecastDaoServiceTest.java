package org.tpokora.persistance.services.weather;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.tpokora.persistance.common.utils.ForecastTestsHelper;
import org.tpokora.persistance.entity.weather.ForecastEntity;
import org.tpokora.persistance.repositories.weather.IForecastRepository;
import org.tpokora.persistance.services.BaseServiceTest;

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ForecastDaoServiceTest extends BaseServiceTest {

    @Autowired
    private IForecastRepository forecastRepository;

    private ForecastDaoService forecastDaoService;

    @BeforeEach
    public void setup() {
        forecastDaoService = new ForecastDaoService(forecastRepository);
    }

    @Test
    public void testSaveForecast() {
        ForecastEntity firstForecastEntity = ForecastTestsHelper.createForecast();
        forecastDaoService.saveForecast(firstForecastEntity);
        List<ForecastEntity> allByCoordinates = forecastDaoService.findAllByCoordinates(firstForecastEntity.getLongitude(), firstForecastEntity.getLatitude());
        Assertions.assertEquals(1, allByCoordinates.size());

        ForecastEntity secondForecastEntity = ForecastTestsHelper.createForecast();
        forecastDaoService.saveForecast(secondForecastEntity);

        allByCoordinates = forecastDaoService.findAllByCoordinates(firstForecastEntity.getLongitude(), firstForecastEntity.getLatitude());
        Assertions.assertEquals(1, allByCoordinates.size());

        LocalDateTime newTimestamp = secondForecastEntity.getTimestamp();
        newTimestamp = newTimestamp.plusMinutes(65);
        secondForecastEntity.setTimestamp(newTimestamp);
        forecastDaoService.saveForecast(secondForecastEntity);
        allByCoordinates = forecastDaoService.findAllByCoordinates(firstForecastEntity.getLongitude(), firstForecastEntity.getLatitude());
        Assertions.assertEquals(2, allByCoordinates.size());
    }

}
