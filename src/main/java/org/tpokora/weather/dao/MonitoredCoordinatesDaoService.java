package org.tpokora.weather.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.tpokora.users.model.User;
import org.tpokora.weather.model.entity.MonitoredCoordinatesEntity;

import java.util.List;

@Service
public class MonitoredCoordinatesDaoService {

    private final Logger LOGGER = LoggerFactory.getLogger(MonitoredCoordinatesDaoService.class);

    private IMonitoredCoordinatesRepository monitoredCoordinatesRepository;

    public MonitoredCoordinatesDaoService(IMonitoredCoordinatesRepository monitoredCoordinatesRepository) {
        this.monitoredCoordinatesRepository = monitoredCoordinatesRepository;
    }

    public MonitoredCoordinatesEntity save(MonitoredCoordinatesEntity monitoredCoordinatesEntity) {
        LOGGER.info(">>> Saving {}", monitoredCoordinatesEntity.toString());
        return monitoredCoordinatesRepository.saveAndFlush(monitoredCoordinatesEntity);
    }

    public List<MonitoredCoordinatesEntity> findByUser(User user) {
        LOGGER.info(">>> Looking for MonitoredCoordinatesEntity by user: {}", user.getUsername());
        return monitoredCoordinatesRepository.findAllByUser_Id(user.getId());
    }

    public void deleteById(int id) {
        LOGGER.info(">>> Delete MonitoredCoordinatesEntity with id: {}", id);
        monitoredCoordinatesRepository.deleteById(id);
    }
}
