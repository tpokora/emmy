package org.tpokora.storms.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.tpokora.storms.model.WarningEntity;

import java.util.List;
import java.util.Optional;

@Service
public class WarningDaoService {

    private final Logger LOGGER = LoggerFactory.getLogger(WarningDaoService.class);

    private WarningRepository warningsRepository;

    public WarningDaoService(WarningRepository warningsRepository) {
        this.warningsRepository = warningsRepository;
    }

    public WarningEntity save(WarningEntity warningEntity) {
        return warningsRepository.save(warningEntity);
    }

    public WarningEntity getById(int id) {
        return warningsRepository.getOne(id);
    }

    public List<WarningEntity> getAll() {
        return warningsRepository.findAll();
    }

    public Optional<WarningEntity> getSameWarning(WarningEntity warningEntity) {
        return warningsRepository.findSameWarning(warningEntity.getName(),
                warningEntity.getLevel(), warningEntity.getStart(), warningEntity.getEnd());
    }
}
