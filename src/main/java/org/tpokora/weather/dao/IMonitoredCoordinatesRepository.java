package org.tpokora.weather.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tpokora.weather.model.entity.MonitoredCoordinatesEntity;

import java.util.List;

public interface IMonitoredCoordinatesRepository extends JpaRepository<MonitoredCoordinatesEntity, Integer> {

    List<MonitoredCoordinatesEntity> findAllByUser_Id(Integer userId);
}
