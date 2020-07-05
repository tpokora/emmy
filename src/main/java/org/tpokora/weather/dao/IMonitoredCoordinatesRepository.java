package org.tpokora.weather.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tpokora.weather.model.entity.MonitoredCoordinatesEntity;

public interface IMonitoredCoordinatesRepository extends JpaRepository<MonitoredCoordinatesEntity, Integer> {


}
