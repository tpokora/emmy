package org.tpokora.storms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.tpokora.common.model.Notification;
import org.tpokora.common.services.AndroidPushNotificationsService;
import org.tpokora.config.properties.NotificationProperties;
import org.tpokora.storms.model.Coordinates;
import org.tpokora.storms.model.Warning;

import javax.xml.soap.SOAPException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Profile("crone")
@Component
public class WeatherScheduledJobsService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherScheduledJobsService.class);
    private static final String WEATHER_WARNINGS_CRONE = "0 0 0,6,12,18 * * *";

    private FindWarningService findWarningService;
    AndroidPushNotificationsService androidPushNotificationsService;

    private NotificationProperties notificationProperties;

    public WeatherScheduledJobsService(FindWarningService findWarningService,
                                       AndroidPushNotificationsService androidPushNotificationsService,
                                       NotificationProperties notificationProperties) {
        this.findWarningService = findWarningService;
        this.androidPushNotificationsService = androidPushNotificationsService;
        this.notificationProperties = notificationProperties;
    }

    @Scheduled(cron = WEATHER_WARNINGS_CRONE)
//    @Scheduled(fixedRate = 30000)
    public void checkWeatherWarningsJob() throws IOException, SOAPException {

        logger.info("Check for weather warnings");
        List<Warning> warnings = checkWeatherWarnings();
        for (Warning warning : warnings) {
            logger.info(warning.toString());
            HttpEntity<String> request = new HttpEntity<>(androidPushNotificationsService.generatePushNotificationJSON(Notification.createNotificationFromWarning(warning)).toString());
            CompletableFuture<String> pushNotification = androidPushNotificationsService.sendNotification(request);
        }
    }

    private List<Warning> checkWeatherWarnings() throws IOException, SOAPException {
        Double coordinateX = Double.parseDouble(this.notificationProperties.getValue(NotificationProperties.COORDINATE_X));
        Double coordinateY = Double.parseDouble(this.notificationProperties.getValue(NotificationProperties.COORDINATE_Y));
        List<Warning> warnings = findWarningService.findWarnings(new Coordinates(coordinateX, coordinateY));
        return warnings;
    }
}
