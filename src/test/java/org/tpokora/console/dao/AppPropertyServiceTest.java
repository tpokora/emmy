package org.tpokora.console.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tpokora.common.services.BaseServiceTest;
import org.tpokora.console.model.entity.AppPropertyEntity;

import java.util.Optional;

class AppPropertyServiceTest extends BaseServiceTest {

    public static final String TEST_PROPERTY = "TEST_PROPERTY";
    public static final String TEST_VALUE = "TEST_VALUE";
    public static final String TEST_DESCRIPTION = "TEST_DESCRIPTION";
    public static final int TEST_ID = 1;

    @Autowired
    private IAppPropertyRepository appPropertyRepository;

    private AppPropertyService appPropertyService;

    @BeforeEach
    public void setup() {
        appPropertyService = new AppPropertyService(appPropertyRepository);
        AppPropertyEntity testAppPropertyEntity = new AppPropertyEntity();
        testAppPropertyEntity.setId(TEST_ID);
        testAppPropertyEntity.setProperty(TEST_PROPERTY);
        testAppPropertyEntity.setValue(TEST_VALUE);
        testAppPropertyEntity.setDescription(TEST_DESCRIPTION);
        appPropertyService.saveProperty(testAppPropertyEntity.getProperty(),
                testAppPropertyEntity.getValue(), testAppPropertyEntity.getDescription());
    }

    @AfterEach
    public void teardown() {
        appPropertyRepository.deleteAll();
    }

    @Test
    void testGetProperty() {
        Optional<AppPropertyEntity> notExistingProperty = appPropertyService.getProperty("property");
        Assertions.assertFalse(notExistingProperty.isPresent());

        Optional<AppPropertyEntity> property = appPropertyService.getProperty(TEST_PROPERTY);
        Assertions.assertTrue(property.isPresent());
        AppPropertyEntity appPropertyEntity = property.get();
        Assertions.assertEquals(TEST_PROPERTY, appPropertyEntity.getProperty());
        Assertions.assertEquals(TEST_VALUE, appPropertyEntity.getValue());
        Assertions.assertEquals(TEST_DESCRIPTION, appPropertyEntity.getDescription());

        String appPropertyEntityExpectedString = "AppPropertyEntity{" +
                "id=" + appPropertyEntity.getId() +
                ", property='" + appPropertyEntity.getProperty() + '\'' +
                ", value='" + appPropertyEntity.getValue() + '\'' +
                ", description='" + appPropertyEntity.getDescription() + '\'' +
                '}';

        Assertions.assertEquals(appPropertyEntityExpectedString, appPropertyEntity.toString());
    }

    @Test
    void testSaveExistingProperty() {
        String updatedValue = "UPDATED_VALUE";
        String updatedDescription = "UPDATED_DESCRIPTION";
        AppPropertyEntity updatedAppPropertyEntity = new AppPropertyEntity(TEST_PROPERTY, updatedValue, updatedDescription);

        appPropertyService.saveProperty(updatedAppPropertyEntity.getProperty(), updatedAppPropertyEntity.getValue(), updatedAppPropertyEntity.getDescription());

        Optional<AppPropertyEntity> updatedAppPropertyEntityOptional = appPropertyService.getProperty(updatedAppPropertyEntity.getProperty());
        updatedAppPropertyEntity = updatedAppPropertyEntityOptional.get();
        Assertions.assertEquals(TEST_PROPERTY, updatedAppPropertyEntity.getProperty());
        Assertions.assertEquals(updatedValue, updatedAppPropertyEntity.getValue());
        Assertions.assertEquals(updatedDescription, updatedAppPropertyEntity.getDescription());
    }


}