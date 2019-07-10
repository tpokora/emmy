package org.tpokora.storms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.tpokora.storms.model.Coordinates;
import org.tpokora.storms.model.Warning;

import javax.xml.soap.SOAPException;
import java.io.IOException;
import java.util.Set;

@Profile("crone")
@Component
public class WeatherScheduledJobsService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherScheduledJobsService.class);

    @Autowired
    private FindWarningService findWarningService;

    @Scheduled(fixedRate = 30000)
    public void checkWeatherWarningsJob() throws IOException, SOAPException {
        logger.info("Check for weather warnings");
        Set<Warning> warnings = checkWeatherWarnings();
        for (Warning warning : warnings) {
            logger.info(warning.toString());
        }
    }

    private Set<Warning> checkWeatherWarnings() throws IOException, SOAPException {
        String weather_scheduler_x = "19.49";
        String weather_scheduler_y = "49.59";
        Set<Warning> warnings = findWarningService.handleResponse(findWarningService.findWarning(new Coordinates(weather_scheduler_x, weather_scheduler_y)));
        return warnings;
    }
}
