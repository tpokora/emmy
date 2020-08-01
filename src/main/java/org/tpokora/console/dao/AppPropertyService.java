package org.tpokora.console.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.tpokora.console.model.entity.AppPropertyEntity;

import java.util.Optional;

@Service
public class AppPropertyService {

    private final Logger LOGGER = LoggerFactory.getLogger(AppPropertyService.class);

    private final IAppPropertyRepository appPropertyRepository;

    public AppPropertyService(IAppPropertyRepository appPropertyRepository) {
        this.appPropertyRepository = appPropertyRepository;
    }

    public Optional<AppPropertyEntity> getProperty(String property) {
        return appPropertyRepository.findByProperty(property);
    }

    public void saveProperty(String property, String value, String description) {
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
}
