package org.tpokora.persistance.services.weather;

import org.junit.jupiter.api.AfterEach;
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
import java.util.Arrays;
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

    @AfterEach
    public void teardown() {
        forecastRepository.deleteAll();
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

    @Test
    void testFindAllByCoordinatesBetweenDates() {
        LocalDateTime startDate = LocalDateTime.now().minusDays(10);
        LocalDateTime endDate = LocalDateTime.now().minusDays(3);

        ForecastEntity forecastEntityOne = ForecastTestsHelper.createForecast(LocalDateTime.now().minusDays(11));
        ForecastEntity forecastEntityTwo = ForecastTestsHelper.createForecast(LocalDateTime.now().minusDays(8));
        ForecastEntity forecastEntityThree = ForecastTestsHelper.createForecast(LocalDateTime.now().minusDays(5));
        ForecastEntity forecastEntityFour = ForecastTestsHelper.createForecast(LocalDateTime.now().minusDays(1));

        forecastRepository.saveAll(Arrays.asList(forecastEntityOne, forecastEntityTwo, forecastEntityThree, forecastEntityFour));

        Assertions.assertEquals(2,
                forecastDaoService.findAllByCoordinatesBetweenDates(11.11, 22.11, startDate, endDate).size());
    }

    @Test
    void testFindAllByLocationBetweenDates() {
        LocalDateTime startDate = LocalDateTime.now().minusDays(10);
        LocalDateTime endDate = LocalDateTime.now().minusDays(3);

        ForecastEntity forecastEntityOne = ForecastTestsHelper.createForecast(LocalDateTime.now().minusDays(11));
        ForecastEntity forecastEntityTwo = ForecastTestsHelper.createForecast(LocalDateTime.now().minusDays(8));
        ForecastEntity forecastEntityThree = ForecastTestsHelper.createForecast(LocalDateTime.now().minusDays(5));
        ForecastEntity forecastEntityFour = ForecastTestsHelper.createForecast(LocalDateTime.now().minusDays(1));

        forecastRepository.saveAll(Arrays.asList(forecastEntityOne, forecastEntityTwo, forecastEntityThree, forecastEntityFour));

        Assertions.assertEquals(2, forecastDaoService.findAllByLocationBetweenDates("testLocation", startDate, endDate).size());
    }
}
