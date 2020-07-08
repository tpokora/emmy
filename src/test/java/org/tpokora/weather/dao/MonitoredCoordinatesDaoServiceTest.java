package org.tpokora.weather.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.tpokora.common.services.BaseServiceTest;
import org.tpokora.users.dao.UserRepository;
import org.tpokora.users.model.User;
import org.tpokora.weather.model.entity.MonitoredCoordinatesEntity;

import java.util.List;

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

    @AfterEach
    void tearDown() {
        List<MonitoredCoordinatesEntity> all = monitoredCoordinatesDaoService.getAll();
        all.forEach(entity -> monitoredCoordinatesDaoService.deleteById(entity.getId()));
    }

    @Test
    void testSave() {
        MonitoredCoordinatesEntity monitoredCoordinatesEntity = new MonitoredCoordinatesEntity();
        monitoredCoordinatesEntity.setUser(USER);
        monitoredCoordinatesEntity.setLocationName("TestLocation");
        monitoredCoordinatesEntity.setLongitude(11.1111);
        monitoredCoordinatesEntity.setLongitudeDM(11.23);
        monitoredCoordinatesEntity.setLatitude(22.2222);
        monitoredCoordinatesEntity.setLatitudeDM(22.23);

        MonitoredCoordinatesEntity saved = monitoredCoordinatesDaoService.save(monitoredCoordinatesEntity);
        Assertions.assertNotNull(saved);
        Assertions.assertEquals(monitoredCoordinatesEntity.getLocationName(), saved.getLocationName());
        Assertions.assertEquals(monitoredCoordinatesEntity.getLongitude(), saved.getLongitude());
        Assertions.assertEquals(monitoredCoordinatesEntity.getLongitudeDM(), saved.getLongitudeDM());
        Assertions.assertEquals(monitoredCoordinatesEntity.getLatitude(), saved.getLatitude());
        Assertions.assertEquals(monitoredCoordinatesEntity.getLatitudeDM(), saved.getLatitudeDM());
        Assertions.assertEquals(monitoredCoordinatesEntity.getUser().getId(), saved.getUser().getId());
    }

    @Test
    void testSaveTheSameCoordinates_fails() {
        MonitoredCoordinatesEntity monitoredCoordinatesEntity = new MonitoredCoordinatesEntity();
        monitoredCoordinatesEntity.setUser(USER);
        monitoredCoordinatesEntity.setLocationName("TestLocation");
        monitoredCoordinatesEntity.setLongitude(11.1111);
        monitoredCoordinatesEntity.setLongitudeDM(11.23);
        monitoredCoordinatesEntity.setLatitude(22.2222);
        monitoredCoordinatesEntity.setLatitudeDM(22.23);

        MonitoredCoordinatesEntity saved = monitoredCoordinatesDaoService.save(monitoredCoordinatesEntity);
        Assertions.assertNotNull(saved);

        MonitoredCoordinatesEntity theSameEntity = new MonitoredCoordinatesEntity();
        theSameEntity.setUser(USER);
        theSameEntity.setLocationName("TestLocation");
        theSameEntity.setLongitude(11.1111);
        theSameEntity.setLongitudeDM(11.23);
        theSameEntity.setLatitude(22.2222);
        theSameEntity.setLatitudeDM(22.23);
        MonitoredCoordinatesEntity notSaved = monitoredCoordinatesDaoService.save(theSameEntity);
        Assertions.assertNull(notSaved);
    }

    @Test
    void testFindByUser() {
        MonitoredCoordinatesEntity monitoredCoordinatesEntity = new MonitoredCoordinatesEntity();
        monitoredCoordinatesEntity.setUser(USER);
        monitoredCoordinatesEntity.setLongitude(11.1111);
        monitoredCoordinatesEntity.setLongitudeDM(11.23);
        monitoredCoordinatesEntity.setLatitude(22.2222);
        monitoredCoordinatesEntity.setLatitudeDM(22.23);
        monitoredCoordinatesDaoService.save(monitoredCoordinatesEntity);

        User secondUser = new User();
        userRepository.save(secondUser);

        MonitoredCoordinatesEntity secondMonitoredCoordinatesEntity = new MonitoredCoordinatesEntity();
        secondMonitoredCoordinatesEntity.setUser(secondUser);
        secondMonitoredCoordinatesEntity.setLongitude(11.1111);
        secondMonitoredCoordinatesEntity.setLongitudeDM(11.23);
        secondMonitoredCoordinatesEntity.setLatitude(22.2222);
        secondMonitoredCoordinatesEntity.setLatitudeDM(22.23);
        monitoredCoordinatesDaoService.save(secondMonitoredCoordinatesEntity);

        List<MonitoredCoordinatesEntity> monitoredCoordinatesDaoServiceByUser = monitoredCoordinatesDaoService.findByUser(USER);
        Assertions.assertEquals(1, monitoredCoordinatesDaoServiceByUser.size());
    }

    @Test
    void testDeleteById() {
        MonitoredCoordinatesEntity monitoredCoordinatesEntity = new MonitoredCoordinatesEntity();
        monitoredCoordinatesEntity.setUser(USER);
        monitoredCoordinatesEntity.setLongitude(11.1111);
        monitoredCoordinatesEntity.setLongitudeDM(11.23);
        monitoredCoordinatesEntity.setLatitude(22.2222);
        monitoredCoordinatesEntity.setLatitudeDM(22.23);
        monitoredCoordinatesDaoService.save(monitoredCoordinatesEntity);

        List<MonitoredCoordinatesEntity> monitoredCoordinatesDaoServiceByUser = monitoredCoordinatesDaoService.findByUser(USER);
        Assertions.assertEquals(1, monitoredCoordinatesDaoServiceByUser.size());
        monitoredCoordinatesDaoService.deleteById(monitoredCoordinatesEntity.getId());

        monitoredCoordinatesDaoServiceByUser = monitoredCoordinatesDaoService.findByUser(USER);
        Assertions.assertTrue(monitoredCoordinatesDaoServiceByUser.isEmpty());
    }

    @Test
    public void testGetAll() {
        MonitoredCoordinatesEntity monitoredCoordinatesEntity = new MonitoredCoordinatesEntity();
        monitoredCoordinatesEntity.setUser(USER);
        monitoredCoordinatesEntity.setLongitude(11.1111);
        monitoredCoordinatesEntity.setLongitudeDM(11.23);
        monitoredCoordinatesEntity.setLatitude(22.2222);
        monitoredCoordinatesEntity.setLatitudeDM(22.23);
        monitoredCoordinatesDaoService.save(monitoredCoordinatesEntity);

        MonitoredCoordinatesEntity secondMonitoredCoordinatesEntity = new MonitoredCoordinatesEntity();
        secondMonitoredCoordinatesEntity.setUser(USER);
        secondMonitoredCoordinatesEntity.setLongitude(11.2222);
        secondMonitoredCoordinatesEntity.setLongitudeDM(11.5555);
        secondMonitoredCoordinatesEntity.setLatitude(22.2222);
        secondMonitoredCoordinatesEntity.setLatitudeDM(22.23);
        monitoredCoordinatesDaoService.save(secondMonitoredCoordinatesEntity);

        Assertions.assertEquals(2, monitoredCoordinatesDaoService.getAll().size());

    }
}