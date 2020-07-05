package org.tpokora.weather.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.tpokora.weather.model.entity.WarningEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WarningDaoService {

    private final Logger LOGGER = LoggerFactory.getLogger(WarningDaoService.class);

    private IWarningRepository warningsRepository;

    public WarningDaoService(IWarningRepository warningsRepository) {
        this.warningsRepository = warningsRepository;
    }

    public Optional<WarningEntity> save(WarningEntity warningEntity) {
        if (getSameWarning(warningEntity).isEmpty()) {
            Optional<WarningEntity> savedWarningEntity = Optional.of(warningsRepository.save(warningEntity));
            savedWarningEntity.ifPresent(entity -> LOGGER.info(">>> Saved: {}", entity));
            return savedWarningEntity;
        }
        return Optional.empty();
    }

    public List<WarningEntity> saveAll(List<WarningEntity> warningEntityList) {
        warningEntityList.forEach(this::save);
        List<Integer> ids = warningEntityList.stream().map(WarningEntity::getId).collect(Collectors.toList());
        List<WarningEntity> warningEntitiesById = warningsRepository.findAllById(ids);
        return warningEntitiesById;
    }

    public Optional<WarningEntity> getById(int id) {
        return Optional.of(warningsRepository.getOne(id));
    }

    public List<WarningEntity> getAll() {
        return warningsRepository.findAll();
    }

    public Optional<WarningEntity> getSameWarning(WarningEntity warningEntity) {
        return warningsRepository.findSameWarning(warningEntity.getLongitude(), warningEntity.getLatitude(),
                warningEntity.getName(), warningEntity.getLevel(),
                warningEntity.getStart(), warningEntity.getEnd());
    }
}
