package org.tpokora.weather.model.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tpokora.users.model.User;
import org.tpokora.weather.model.Coordinates;
import org.tpokora.weather.model.Location;

public class MonitoredCoordinatesEntityTest {

    @Test
    public void testMonitoredCoordinatesEntity() {
        MonitoredCoordinatesEntity monitoredCoordinatesEntity = new MonitoredCoordinatesEntity();
        monitoredCoordinatesEntity.setId(1);
        monitoredCoordinatesEntity.setLocationName("testLocation");
        monitoredCoordinatesEntity.setLongitudeDM(11.23);
        monitoredCoordinatesEntity.setLatitudeDM(11.25);
        monitoredCoordinatesEntity.setLongitude(11.11);
        monitoredCoordinatesEntity.setLatitude(11.22);

        String expectedString = String.format(
                "MonitoredCoordinatesEntity{id=%d, locationName='%s', longitude=%s, latitude=%s, longitudeDM=%s, latitudeDM=%s, user=null}",
                monitoredCoordinatesEntity.getId(), monitoredCoordinatesEntity.getLocationName(), monitoredCoordinatesEntity.getLongitude(),
                monitoredCoordinatesEntity.getLatitude(), monitoredCoordinatesEntity.getLongitudeDM(), monitoredCoordinatesEntity.getLatitudeDM());
        Assertions.assertEquals(expectedString, monitoredCoordinatesEntity.toString());
    }

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
