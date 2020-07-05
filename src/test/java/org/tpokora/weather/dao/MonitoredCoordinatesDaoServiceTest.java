package org.tpokora.weather.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tpokora.common.services.BaseServiceTest;
import org.tpokora.users.dao.UserRepository;
import org.tpokora.users.model.User;
import org.tpokora.weather.model.entity.MonitoredCoordinatesEntity;

class MonitoredCoordinatesDaoServiceTest extends BaseServiceTest {

    @Autowired
    private UserRepository userRepository;
    private User USER;

    @Autowired
    private IMonitoredCoordinatesRepository monitoredCoordinatesRepository;

    private MonitoredCoordinatesDaoService monitoredCoordinatesDaoService;

    @BeforeEach
    void setUp() {
        monitoredCoordinatesDaoService = new MonitoredCoordinatesDaoService(monitoredCoordinatesRepository);
        USER = new User();
        userRepository.save(USER);
    }

    @Test
    void testSave() {
        MonitoredCoordinatesEntity monitoredCoordinatesEntity = new MonitoredCoordinatesEntity();
        monitoredCoordinatesEntity.setUser(USER);
        monitoredCoordinatesEntity.setLongitude(11.1111);
        monitoredCoordinatesEntity.setLongitudeDM(11.23);
        monitoredCoordinatesEntity.setLatitude(22.2222);
        monitoredCoordinatesEntity.setLatitudeDM(22.23);

        MonitoredCoordinatesEntity saved = monitoredCoordinatesDaoService.save(monitoredCoordinatesEntity);
        Assertions.assertNotNull(saved);
        Assertions.assertEquals(monitoredCoordinatesEntity.getLongitude(), saved.getLongitude());
        Assertions.assertEquals(monitoredCoordinatesEntity.getLongitudeDM(), saved.getLongitudeDM());
        Assertions.assertEquals(monitoredCoordinatesEntity.getLatitude(), saved.getLatitude());
        Assertions.assertEquals(monitoredCoordinatesEntity.getLatitudeDM(), saved.getLatitudeDM());
        Assertions.assertEquals(monitoredCoordinatesEntity.getUser().getId(), saved.getUser().getId());
    }
}