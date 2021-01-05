package org.tpokora.persistance.repositories.weather;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tpokora.persistance.entity.weather.MonitoredCoordinatesEntity;

import java.util.List;

public interface IMonitoredCoordinatesRepository extends JpaRepository<MonitoredCoordinatesEntity, Integer> {

    List<MonitoredCoordinatesEntity> findAllByUser_Id(Integer userId);
}
