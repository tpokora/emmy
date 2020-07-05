package org.tpokora.weather.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.tpokora.weather.model.entity.MonitoredCoordinatesEntity;

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
}
