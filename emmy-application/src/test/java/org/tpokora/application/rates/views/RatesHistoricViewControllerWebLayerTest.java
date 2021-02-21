package org.tpokora.application.rates.views;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.tpokora.application.common.views.BaseViewControllerWebLayerTest;
import org.tpokora.common.utils.DateUtils;
import org.tpokora.persistance.entity.rates.RateEntity;
import org.tpokora.persistance.services.rates.IRatesDaoService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RatesHistoricViewController.class)
public class RatesHistoricViewControllerWebLayerTest extends BaseViewControllerWebLayerTest {

    public static final String RATES = "/rates";

    public static final String HISTORIC_RATES = RATES + "/historicRates";
    public static final String HISTORIC_RATES_FIND = HISTORIC_RATES + "/find";

    public static final String HISTORIC_RATES_DATA_MESSAGE = "<h1>Historic Rates Data</h1>";

    @MockBean
    @Qualifier("ratesDaoJpaService")
    IRatesDaoService ratesDaoService;

    @Test
    @WithMockUser
    void testHistoricRatesHome() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get(HISTORIC_RATES)).andDo(print()).andExpect(status().isOk()).andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        assertBasicContent(responseBody);
    }

    @Test
    @WithMockUser
    void testHistoricRatesFind_noRatesFound() throws Exception {
        Mockito.when(ratesDaoService.getRatesDaysBeforeToday(anyString(), anyString(), anyInt())).thenReturn(Collections.emptyList());
        MvcResult mvcResult = this.mockMvc.perform(get(HISTORIC_RATES_FIND + "?from=&to=")).andDo(print()).andExpect(status().isOk()).andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        assertBasicContent(responseBody);

        String fieldRequired = "Field required";
        Assertions.assertTrue(responseBody.contains(fieldRequired));
    }

    @Test
    @WithMockUser
    void testHistoricRatesFind_ratesFound() throws Exception {
        List<RateEntity> mockRateList = createMockRateList();
        Mockito.when(ratesDaoService.getRatesDaysBeforeToday(anyString(), anyString(), anyInt())).thenReturn(mockRateList);
        MvcResult mvcResult = this.mockMvc.perform(get(HISTORIC_RATES_FIND + "?from=XAU&to=USD")).andDo(print()).andExpect(status().isOk()).andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        assertBasicContent(responseBody);

        mockRateList.forEach(rateEntity -> {
            String nameElement = tableElementString(rateEntity.getName());
            String fromElement = tableElementString(String.valueOf(rateEntity.getFrom()));
            String toElement = tableElementString(String.valueOf(rateEntity.getTo()));
            String valueElement = tableElementString(String.valueOf(rateEntity.getValue()));
            String timestampElement = tableElementString(DateUtils.parseDateToString(rateEntity.getTimestamp()));

            Assertions.assertTrue(responseBody.contains(nameElement));
            Assertions.assertTrue(responseBody.contains(fromElement));
            Assertions.assertTrue(responseBody.contains(toElement));
            Assertions.assertTrue(responseBody.contains(valueElement));
            Assertions.assertTrue(responseBody.contains(timestampElement));
        });
    }

    private List<RateEntity> createMockRateList() {
        List<RateEntity> rateEntityList = new ArrayList<>();
        RateEntity rateEntity1 = new RateEntity("TestRate", "XAU", "USD", 1.1, LocalDateTime.now().minusDays(1));
        RateEntity rateEntity2 = new RateEntity("TestRate", "XAU", "USD", 1.2, LocalDateTime.now().minusDays(2));
        rateEntityList.add(rateEntity1);
        rateEntityList.add(rateEntity2);
        return rateEntityList;
    }

    private void assertBasicContent(String body) {
        Assertions.assertTrue(body.contains(HISTORIC_RATES_DATA_MESSAGE));
    }

    private String tableElementString(String value) {
        return String.format("<td>%s</td>", value);
    }
}
