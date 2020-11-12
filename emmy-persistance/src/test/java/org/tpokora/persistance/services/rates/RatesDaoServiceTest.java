//package org.tpokora.persistance.services.rates;
//
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.tpokora.persistance.entity.rates.RateEntity;
//import org.tpokora.persistance.repositories.rates.RatesRepository;
//
//import java.time.LocalDateTime;
//
//public class RatesDaoServiceTest {
//
//    public static final String FROM = "from";
//    public static final String TO = "to";
//
//    @Autowired
//    private RatesRepository ratesRepository;
//
//    private RatesDaoService ratesDaoService;
//
//    @BeforeEach
//    public void setup() {
//        ratesDaoService = new RatesDaoService(ratesRepository);
//    }
//
//    @AfterEach
//    public void tearDown() {
//        ratesRepository.deleteAll();
//    }
//
//    @Test
//    public void testSaveRate() {
//        RateEntity rateEntity = new RateEntity("name", FROM, TO, 1.1, LocalDateTime.now());
//        RateEntity rateFromDB = saveRateEntity(rateEntity);
//
//        assertRateEntity(rateEntity, rateFromDB);
//    }
//
//    @Test
//    public void testGetRatesDaysBeforeToday() {
//        RateEntity rateEntity = new RateEntity("name", FROM, TO, 1.1, LocalDateTime.now());
//        RateEntity pastRateEntity = new RateEntity("name", FROM, TO, 2.2, LocalDateTime.now().minusDays(10));
//
//        saveRateEntity(rateEntity);
//        saveRateEntity(pastRateEntity);
//
//        Assertions.assertEquals(1, ratesDaoService.getRatesDaysBeforeToday(FROM, TO, 4).size());
//        Assertions.assertEquals(2, ratesDaoService.getRatesDaysBeforeToday(FROM, TO, 12).size());
//    }
//
//    private RateEntity saveRateEntity(RateEntity rateEntity) {
//        return ratesDaoService.saveRate(rateEntity);
//    }
//
//    private void assertRateEntity(RateEntity expectedRateEntity, RateEntity givenRateEntity) {
//        Assertions.assertEquals(expectedRateEntity.getName(), givenRateEntity.getName());
//        Assertions.assertEquals(expectedRateEntity.getFrom(), givenRateEntity.getFrom());
//        Assertions.assertEquals(expectedRateEntity.getTo(), givenRateEntity.getTo());
//        Assertions.assertEquals(expectedRateEntity.getValue(), givenRateEntity.getValue());
//        Assertions.assertEquals(expectedRateEntity.getTimestamp(), givenRateEntity.getTimestamp());
//    }
//}
