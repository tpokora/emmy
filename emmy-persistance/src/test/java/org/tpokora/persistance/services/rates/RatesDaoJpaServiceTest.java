package org.tpokora.persistance.services.rates;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.tpokora.common.utils.DateUtils;
import org.tpokora.persistance.entity.rates.RateEntity;
import org.tpokora.persistance.repositories.rates.RatesRepository;
import org.tpokora.persistance.services.BaseServiceTest;

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RatesDaoJpaServiceTest extends BaseServiceTest {

    public static final String FROM = "from";
    public static final String TO = "to";

    @Autowired
    private RatesRepository ratesRepository;

    private RatesDaoJpaService ratesDaoJpaService;

    @BeforeEach
    public void setup() {
        ratesDaoJpaService = new RatesDaoJpaService(ratesRepository);
    }

    @AfterEach
    public void tearDown() {
        ratesRepository.deleteAll();
    }

    @Test
    void testSaveRate() {
        RateEntity rateEntity = new RateEntity("name", FROM, TO, 1.1, LocalDateTime.now());
        RateEntity rateFromDB = saveRateEntity(rateEntity);

        assertRateEntity(rateEntity, rateFromDB);
    }

    @Test
    public void testGetRatesDaysBeforeToday() {
        RateEntity rateEntity = new RateEntity("name", FROM, TO, 1.1, LocalDateTime.now());
        RateEntity pastRateEntity = new RateEntity("name", FROM, TO, 2.2, LocalDateTime.now().minusDays(10));

        saveRateEntity(rateEntity);
        saveRateEntity(pastRateEntity);

        Assertions.assertEquals(1, ratesDaoJpaService.getRatesDaysBeforeToday(FROM, TO, 4).size());
        Assertions.assertEquals(2, ratesDaoJpaService.getRatesDaysBeforeToday(FROM, TO, 12).size());
    }

    @Test
    public void testGetRatesDaysFromTimestamp() {
        RateEntity rateEntityOne = new RateEntity("name", FROM, TO, 1.1, DateUtils.parseStringToDateTime("2020-11-20 19:20:30"));
        RateEntity rateEntityTwo = new RateEntity("name", FROM, TO, 1.1, DateUtils.parseStringToDateTime("2020-11-20 09:20:30"));
        RateEntity rateEntityThree = new RateEntity("name", FROM, TO, 2.2, DateUtils.parseStringToDateTime("2020-11-19 09:20:30"));
        RateEntity rateEntityFour = new RateEntity("name", FROM, TO, 2.2, DateUtils.parseStringToDateTime("2020-11-21 09:20:30"));

        saveRates(rateEntityOne, rateEntityTwo, rateEntityThree, rateEntityFour);

        LocalDateTime searchLocalDateTime = DateUtils.parseStringToDateTime("2020-11-20 19:20:30");

        List<RateEntity> ratesDaoServiceRatesForDate = ratesDaoJpaService.getRatesForDate(FROM, TO, searchLocalDateTime);

        Assertions.assertEquals(2, ratesDaoServiceRatesForDate.size());
    }

    @Test
    public void testGetRatesBetweenDates() {
        RateEntity rateEntityOne = new RateEntity("name", FROM, TO, 1.1, DateUtils.parseStringToDateTime("2020-11-18 19:20:30"));
        RateEntity rateEntityTwo = new RateEntity("name", FROM, TO, 1.1, DateUtils.parseStringToDateTime("2020-11-20 09:20:30"));
        RateEntity rateEntityThree = new RateEntity("name", FROM, TO, 2.2, DateUtils.parseStringToDateTime("2020-11-19 09:20:30"));
        RateEntity rateEntityFour = new RateEntity("name", FROM, TO, 2.2, DateUtils.parseStringToDateTime("2020-11-21 09:20:30"));

        saveRates(rateEntityOne, rateEntityTwo, rateEntityThree, rateEntityFour);

        LocalDateTime searchLocalDateTimeStart = DateUtils.parseStringToDateTime("2020-11-19 09:20:30");
        LocalDateTime searchLocalDateTimeEnd = DateUtils.parseStringToDateTime("2020-11-20 19:20:30");

        List<RateEntity> ratesDaoServiceRatesForDate = ratesDaoJpaService.getRatesBetweenDates(FROM, TO, searchLocalDateTimeStart, searchLocalDateTimeEnd);

        Assertions.assertEquals(2, ratesDaoServiceRatesForDate.size());
    }

    private RateEntity saveRateEntity(RateEntity rateEntity) {
        return ratesDaoJpaService.saveRate(rateEntity);
    }

    private void saveRates(RateEntity... rates) {
        for (RateEntity rate : rates) {
            saveRateEntity(rate);
        }
    }

    private void assertRateEntity(RateEntity expectedRateEntity, RateEntity givenRateEntity) {
        Assertions.assertEquals(expectedRateEntity.getName(), givenRateEntity.getName());
        Assertions.assertEquals(expectedRateEntity.getFrom(), givenRateEntity.getFrom());
        Assertions.assertEquals(expectedRateEntity.getTo(), givenRateEntity.getTo());
        Assertions.assertEquals(expectedRateEntity.getValue(), givenRateEntity.getValue());
        Assertions.assertEquals(expectedRateEntity.getTimestamp(), givenRateEntity.getTimestamp());
    }
}
