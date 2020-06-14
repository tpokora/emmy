package org.tpokora.storms.dao;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.tpokora.storms.model.Coordinates;
import org.tpokora.storms.model.StormEntity;
import org.tpokora.storms.model.StormRequest;
import org.tpokora.storms.model.StormResponse;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class StormDaoServiceTests {

    public static final StormRequest STORM_REQUEST = new StormRequest();
    public static final StormResponse STORM_RESPONSE = new StormResponse();

    @Mock
    private StormsRepository stormsRepository;
    @InjectMocks
    private StormDaoService stormDaoService;

    @BeforeEach
    public void setup() {
        STORM_REQUEST.setTime(10);
        STORM_REQUEST.setCoordinates(new Coordinates(11.11, 22.22));
        STORM_REQUEST.setDistance(10);

        STORM_RESPONSE.setTimestamp(LocalDateTime.now());
        STORM_RESPONSE.setTime(STORM_REQUEST.getTime());
        STORM_RESPONSE.setAmount(10);
        STORM_RESPONSE.setDistance(STORM_REQUEST.getDistance());
        STORM_RESPONSE.setDirection("E");
    }

    @Test
    public void testNullObjectsShouldThrowNullPointerException() {
        assertThrows(NullPointerException.class,
                () -> stormDaoService.saveStormResponse(null, null));
    }

    @Test
    public void testSaveStormResponseAmountEqualsZero() {
        StormResponse stormResponse = new StormResponse();
        stormResponse.setAmount(0);
        StormEntity stormEntity = stormDaoService.saveStormResponse(STORM_REQUEST, stormResponse);
        Assertions.assertNull(stormEntity);
    }

    @Test
    public void testSaveStormResponseWhenNoneFoundInDB() {
        StormEntity mockedStormEntity = StormEntity.builder()
                .id(1)
                .amount(STORM_RESPONSE.getAmount())
                .x(String.format("%.2f", STORM_REQUEST.getCoordinates().getX()))
                .y(String.format("%.2f", STORM_REQUEST.getCoordinates().getY()))
                .distance(10)
                .direction("E")
                .time(10)
                .timestamp(LocalDateTime.now())
                .build();
        Mockito.when(stormsRepository.saveAndFlush(any())).thenReturn(mockedStormEntity);
        StormEntity stormEntity = stormDaoService.saveStormResponse(STORM_REQUEST, STORM_RESPONSE);
        Assert.assertEquals(mockedStormEntity.toString(), stormEntity.toString());
    }

    @Test
    public void testSaveStormResponseWhenFoundInDB() {
        StormEntity mockedStormEntity = StormEntity.builder()
                .id(1)
                .amount(STORM_RESPONSE.getAmount())
                .x(String.format("%.2f", STORM_REQUEST.getCoordinates().getX()))
                .y(String.format("%.2f", STORM_REQUEST.getCoordinates().getY()))
                .distance(10)
                .direction("E")
                .time(10)
                .timestamp(LocalDateTime.now())
                .build();
        StormEntity mockedStormEntityFromDB = StormEntity.builder()
                .id(2)
                .amount(STORM_RESPONSE.getAmount())
                .x(String.format("%.2f", STORM_REQUEST.getCoordinates().getX()))
                .y(String.format("%.2f", STORM_REQUEST.getCoordinates().getY()))
                .distance(10)
                .direction("E")
                .time(10)
                .timestamp(LocalDateTime.now().minusMinutes(30))
                .build();
        Mockito.when(stormsRepository.findFirstByLongitudeAndLatitudeOrderByTimestampDesc(anyString(), anyString()))
                .thenReturn(Optional.of(mockedStormEntityFromDB));
        Mockito.when(stormsRepository.saveAndFlush(any())).thenReturn(mockedStormEntity);
        StormEntity stormEntity = stormDaoService.saveStormResponse(STORM_REQUEST, STORM_RESPONSE);
        Assert.assertEquals(mockedStormEntity.toString(), stormEntity.toString());
    }
}
