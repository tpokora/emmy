package org.tpokora.persistance.services.rates;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.tpokora.persistance.entity.rates.RateEntity;
import org.tpokora.persistance.repositories.rates.RatesRepository;
import org.tpokora.persistance.services.BaseServiceTest;

import java.time.LocalDateTime;

public class RateDaoServiceTest extends BaseServiceTest {

    @Autowired
    private RatesRepository ratesRepository;

    private RatesDaoService ratesDaoService;

    @BeforeEach
    public void setup() {
        ratesDaoService = new RatesDaoService(ratesRepository);
    }

    @AfterEach
    public void tearDown() {
        ratesRepository.deleteAll();
    }

    @Test
    public void testSaveRate() {
        LocalDateTime now = LocalDateTime.now();
        RateEntity rateEntity = new RateEntity("name", "from", "to", 1.1, now);
        RateEntity rateFromDB = saveRateEntity(rateEntity);

        assertRateEntity(rateEntity, rateFromDB);
    }

    private RateEntity saveRateEntity(RateEntity rateEntity) {
        return ratesDaoService.saveRate(rateEntity);
    }

    private void assertRateEntity(RateEntity expectedRateEntity, RateEntity givenRateEntity) {
        Assertions.assertEquals(expectedRateEntity.getName(), givenRateEntity.getName());
        Assertions.assertEquals(expectedRateEntity.getFrom(), givenRateEntity.getFrom());
        Assertions.assertEquals(expectedRateEntity.getTo(), givenRateEntity.getTo());
        Assertions.assertEquals(expectedRateEntity.getValue(), givenRateEntity.getValue());
        Assertions.assertEquals(expectedRateEntity.getTimestamp(), givenRateEntity.getTimestamp());
    }
}
