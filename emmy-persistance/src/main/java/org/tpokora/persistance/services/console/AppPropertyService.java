package org.tpokora.persistance.services.console;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.tpokora.persistance.entity.console.AppPropertyEntity;
import org.tpokora.persistance.repositories.console.IAppPropertyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AppPropertyService {

    private final Logger LOGGER = LoggerFactory.getLogger(AppPropertyService.class);

    private final IAppPropertyRepository appPropertyRepository;

    public AppPropertyService(IAppPropertyRepository appPropertyRepository) {
        this.appPropertyRepository = appPropertyRepository;
    }

    public List<AppPropertyEntity> getAllProperties() {
        LOGGER.info(">>> Get all properties");
        return appPropertyRepository.findAll();
    }

    public Optional<AppPropertyEntity> getProperty(String property) {
        LOGGER.info(">>> Get property: {}", property);
        return appPropertyRepository.findByProperty(property);
    }

    public void saveProperty(String property, String value, String description) {
        LOGGER.info(">>> Save property: {}, {}, {}", property, value, description);
        Optional<AppPropertyEntity> propertyOptional = appPropertyRepository.findByProperty(property);
        AppPropertyEntity appPropertyEntity;
        if (propertyOptional.isEmpty()) {
            appPropertyEntity = new AppPropertyEntity(property, value, description);
        } else {
            appPropertyEntity = propertyOptional.get();
            appPropertyEntity.setValue(value);
            appPropertyEntity.setDescription(description);
        }

        appPropertyRepository.save(appPropertyEntity);

    }

    public void deleteProperty(int id) {
        LOGGER.info(">>> Remove property: {}", id);
        appPropertyRepository.deleteById(id);
    }
}
