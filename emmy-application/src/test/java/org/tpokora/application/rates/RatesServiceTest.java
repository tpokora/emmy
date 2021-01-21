package org.tpokora.application.rates;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import org.tpokora.application.common.services.BaseServiceTest;
import org.tpokora.application.rates.properties.GoldAPIProperties;
import org.tpokora.application.rates.services.RatesService;
import org.tpokora.application.rates.services.suppliers.IRatesSupplier;
import org.tpokora.common.utils.DateUtils;
import org.tpokora.persistance.entity.rates.RateEntity;
import org.tpokora.persistance.repositories.rates.RatesRepository;
import org.tpokora.persistance.services.rates.RatesDaoJpaService;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class RatesServiceTest extends BaseServiceTest {

    public static final String XAU = "XAU";
    public static final String USD = "USD";

    @MockBean
    GoldAPIProperties goldAPIProperties;

    @MockBean
    RestTemplate restTemplate;

    @Autowired
    RatesRepository ratesRepository;

    RatesDaoJpaService ratesDaoJpaService;

    @MockBean
    IRatesSupplier ratesSupplier;

    RatesService ratesService;

    @BeforeEach
    public void setup() {
        this.ratesDaoJpaService = new RatesDaoJpaService(ratesRepository);
        this.ratesService = new RatesService(ratesSupplier, ratesDaoJpaService);
    }

    @AfterEach
    public void teardown() {
        ratesRepository.deleteAll();
    }

    @Test
    void testFindRateForDate() {
        LocalDateTime now = LocalDateTime.now();
        RateEntity rateEntity = createRateEntity(XAU, USD, now);

        Mockito.when(ratesSupplier.findRate(XAU, USD, now)).thenReturn(Optional.of(rateEntity));
        Optional<RateEntity> rateForDate = ratesService.findRateForDate(XAU, USD, now);

        Assertions.assertEquals(rateEntity.getFrom(), rateForDate.get().getFrom());
        Assertions.assertEquals(rateEntity.getTo(), rateForDate.get().getTo());
        Assertions.assertEquals(DateUtils.parseDateToString(rateEntity.getTimestamp()), DateUtils.parseDateToString(rateForDate.get().getTimestamp()));
    }

    @Test
    void testSaveRate() {
        RateEntity rateEntity = createRateEntity(XAU, USD, LocalDateTime.now());
        RateEntity savedRateEntity = ratesService.saveRate(rateEntity);

        Assertions.assertEquals(rateEntity.getFrom(), savedRateEntity.getFrom());
        Assertions.assertEquals(rateEntity.getTo(), savedRateEntity.getTo());
        Assertions.assertEquals(DateUtils.parseDateToString(rateEntity.getTimestamp()), DateUtils.parseDateToString(savedRateEntity.getTimestamp()));
    }

    @Test
    void testArchivedRates() {
        String dateString = "2020-12-01 12:00:00";
        LocalDateTime localDateTime = DateUtils.parseStringToDateTime(dateString);
        RateEntity rateEntityOne = createRateEntity(XAU, USD, localDateTime);
        RateEntity rateEntityTwo = createRateEntity(XAU, USD, localDateTime.minusDays(2));
        RateEntity rateEntityThree = createRateEntity(XAU, USD, localDateTime.plusHours(6));

        ratesService.saveRate(rateEntityOne);
        ratesService.saveRate(rateEntityTwo);
        ratesService.saveRate(rateEntityThree);

        LocalDateTime startDate = DateUtils.parseStringToDateTime("2020-12-01 08:00:00");
        LocalDateTime endDate = DateUtils.parseStringToDateTime("2020-12-03 08:00:00");
        Assertions.assertEquals(2,
                ratesService.findArchivedRates(XAU, USD, startDate, endDate).size());
    }

    @Test
    void testFindArchivedRateForDate() {
        String dateString = "2020-12-01 12:00:00";
        LocalDateTime localDateTime = DateUtils.parseStringToDateTime(dateString);
        RateEntity rateEntityOne = createRateEntity(XAU, USD, localDateTime);
        RateEntity rateEntityTwo = createRateEntity(XAU, USD, localDateTime.plusHours(3));
        RateEntity rateEntityThree = createRateEntity(XAU, USD, localDateTime.plusHours(6));

        ratesService.saveRate(rateEntityOne);
        ratesService.saveRate(rateEntityTwo);
        ratesService.saveRate(rateEntityThree);

        LocalDateTime startDate = DateUtils.parseStringToDateTime("2020-12-01 08:00:00");
        Assertions.assertEquals(2,
                ratesService.findArchivedRateForDate(XAU, USD, startDate).size());
    }

    private RateEntity createRateEntity(String from, String to, LocalDateTime localDateTime) {
        RateEntity rateEntity = new RateEntity();
        rateEntity.setName("Test RateEntity");
        rateEntity.setFrom(from);
        rateEntity.setTo(to);
        rateEntity.setTimestamp(localDateTime);
        return rateEntity;
    }
}