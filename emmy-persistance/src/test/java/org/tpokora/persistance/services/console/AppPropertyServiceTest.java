package org.tpokora.persistance.services.console;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.tpokora.persistance.entity.console.AppPropertyEntity;
import org.tpokora.persistance.repositories.console.IAppPropertyRepository;
import org.tpokora.persistance.services.BaseServiceTest;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
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
        updatedAppPropertyEntityOptional.ifPresentOrElse(entity -> {
            Assertions.assertEquals(TEST_PROPERTY, entity.getProperty());
            Assertions.assertEquals(updatedValue, entity.getValue());
            Assertions.assertEquals(updatedDescription, entity.getDescription());
        }, () -> Assertions.fail("Property not found"));
    }

    @Test
    void testGetAllProperties() {
        appPropertyService.saveProperty("NEW_PROPERTY", "NEW_VALUE", "NEW_DESCRIPTION");

        Assertions.assertEquals(2, appPropertyService.getAllProperties().size());
    }

    @Test
    void testDeleteProperties() {
        String newProperty = "NEW_PROPERTY";
        appPropertyService.saveProperty(newProperty, "NEW_VALUE", "NEW_DESCRIPTION");
        AppPropertyEntity property = appPropertyService.getProperty(newProperty).get();
        appPropertyService.deleteProperty(property.getId());
        Assertions.assertTrue(appPropertyService.getProperty(newProperty).isEmpty());
    }


}