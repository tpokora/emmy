package org.tpokora.weather.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.tpokora.weather.dao.MonitoredCoordinatesDaoService;
import org.tpokora.weather.model.Coordinates;
import org.tpokora.weather.model.StormRequest;
import org.tpokora.weather.model.entity.MonitoredCoordinatesEntity;
import org.tpokora.weather.services.forecast.ForecastService;
import org.tpokora.weather.services.storms.FindStormService;
import org.tpokora.weather.services.storms.FindWarningService;

import javax.xml.soap.SOAPException;
import java.util.List;

@Component
public class MonitorCoordinatesScheduler {

    private final Logger LOGGER = LoggerFactory.getLogger(MonitorCoordinatesScheduler.class);

    private MonitoredCoordinatesDaoService monitoredCoordinatesDaoService;
    private ForecastService forecastService;
    private FindStormService findStormService;
    private FindWarningService findWarningService;

    public MonitorCoordinatesScheduler(MonitoredCoordinatesDaoService monitoredCoordinatesDaoService, ForecastService forecastService, FindStormService findStormService, FindWarningService findWarningService) {
        this.monitoredCoordinatesDaoService = monitoredCoordinatesDaoService;
        this.forecastService = forecastService;
        this.findStormService = findStormService;
        this.findWarningService = findWarningService;
    }

    @Scheduled(cron = "0 0 0,4,8,12,16,20 * * *")
    public void monitorCoordinates() {
        LOGGER.info(">>> Check All Coordinates");
        List<MonitoredCoordinatesEntity> monitoredCoordinatesDaoServiceAll = monitoredCoordinatesDaoService.getAll();
        monitoredCoordinatesDaoServiceAll.forEach(this::checkCoordinates);
    }

    private void checkCoordinates(MonitoredCoordinatesEntity entity) {
        LOGGER.info(">>> Check {}: Lon: {}, Lat:{} ", entity.getLocationName(), entity.getLongitude(), entity.getLatitude());
        forecastService.getForecast(entity.getLongitude(), entity.getLatitude());
        StormRequest stormRequest = new StormRequest(entity.getLongitude(), entity.getLatitude(), 10, 15);
        try {
            findStormService.checkStorm(stormRequest);
        } catch (SOAPException e) {
            LOGGER.info(">> Error checking storms");
        }

        try {
            findWarningService.findWarnings(new Coordinates(entity.getLongitude(), entity.getLatitude()));
        } catch (SOAPException e) {
            LOGGER.info(">> Error checking warnings");
        }
    }
}
