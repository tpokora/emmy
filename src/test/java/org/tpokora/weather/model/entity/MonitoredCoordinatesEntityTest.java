package org.tpokora.weather.model.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tpokora.users.model.User;
import org.tpokora.weather.model.Coordinates;
import org.tpokora.weather.model.Location;

public class MonitoredCoordinatesEntityTest {

    @Test
    public void testMonitoredCoordinatesEntityWithLocationAndUser() {
        Coordinates coordinates = new Coordinates();
        coordinates.setLatitude(11.11);
        coordinates.setLongitude(22.22);
        Location location = new Location();
        location.setName("testName");
        location.setCoordinates(coordinates);
        User user = new User();
        user.setId(1);
        MonitoredCoordinatesEntity monitoredCoordinatesEntity = new MonitoredCoordinatesEntity(location, user);

        Assertions.assertEquals(location.getName(), monitoredCoordinatesEntity.getLocationName());
        Assertions.assertEquals(location.getCoordinates().getLongitude(), monitoredCoordinatesEntity.getLongitude());
        Assertions.assertEquals(location.getCoordinates().getLatitude(), monitoredCoordinatesEntity.getLatitude());
        Assertions.assertEquals(location.getCoordinates().getLatitudeDM(), monitoredCoordinatesEntity.getLatitudeDM());
        Assertions.assertEquals(location.getCoordinates().getLongitudeDM(), monitoredCoordinatesEntity.getLongitudeDM());
    }
}
