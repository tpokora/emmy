package org.tpokora.application.rates.views;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.tpokora.application.common.views.BaseViewControllerWebTest;
import org.tpokora.persistance.services.rates.IRatesDaoService;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(classes = {RatesHistoricViewController.class} )
public class RatesHistoricViewControllerWebTest extends BaseViewControllerWebTest {

    @Autowired
    RatesHistoricViewController ratesHistoricViewController;

    @MockBean
    @Qualifier("ratesDaoJpaService")
    IRatesDaoService ratesDaoService;

    @Test
    void contextLoads() throws Exception {
        assertThat(ratesHistoricViewController).isNotNull();
    }

}
