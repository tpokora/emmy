package org.tpokora.console.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.tpokora.console.dao.AppPropertyService;
import org.tpokora.console.model.entity.AppPropertyEntity;
import org.tpokora.console.web.forms.AddLocationForm;
import org.tpokora.console.web.forms.PropertyForm;
import org.tpokora.persistance.entity.users.User;
import org.tpokora.users.model.UserDetailsImpl;
import org.tpokora.persistance.services.weather.MonitoredCoordinatesDaoService;
import org.tpokora.domain.weather.Location;
import org.tpokora.persistance.entity.weather.MonitoredCoordinatesEntity;
import org.tpokora.weather.properties.OpenCageDataProperties;
import org.tpokora.weather.services.location.ILocationService;
import org.tpokora.weather.services.location.OpenCageDataLocationService;

import javax.validation.Valid;
import java.util.Optional;

import static org.tpokora.config.constants.ConsoleViewConstants.*;

@Controller
public class ConsoleViewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleViewController.class);
    public static final String ADD_LOCATION_FORM = "addLocationForm";
    public static final String ADD_PROPERTY_FORM = "addPropertyForm";
    public static final String EDIT_PROPERTY_FORM = "editPropertyForm";

    private UserDetailsImpl userDetails;

    private RestTemplate restTemplate;
    private OpenCageDataProperties openCageDataProperties;

    private ILocationService locationService;
    private MonitoredCoordinatesDaoService monitoredCoordinatesDaoService;
    private AppPropertyService appPropertyService;

    public ConsoleViewController(RestTemplate restTemplate, OpenCageDataProperties openCageDataProperties, MonitoredCoordinatesDaoService monitoredCoordinatesDaoService, AppPropertyService appPropertyService) {
        this.restTemplate = restTemplate;
        this.openCageDataProperties = openCageDataProperties;
        this.monitoredCoordinatesDaoService = monitoredCoordinatesDaoService;
        this.appPropertyService = appPropertyService;
        locationService = new OpenCageDataLocationService(restTemplate, openCageDataProperties);
    }

    @GetMapping(value = CONSOLE_VIEW_URL, name = CONSOLE_VIEW)
    public String home(Model model) {
        LOGGER.info(">> Console");
        initializeForms(model);
        initializeView(model);
        getUserDetails();
        return CONSOLE_VIEW_TEMPLATE;
    }

    @PostMapping(value = CONSOLE_VIEW_URL + "/addLocation")
    public String addLocationMonitorByName(Model model, AddLocationForm locationForm) {
        LOGGER.info(">> Add Location to monitor by name: {}", locationForm.getLocationName());
        getUserDetails();
        Optional<Location> optionalLocation = locationService.getLocationCoordinatesByName(locationForm.getLocationName());
        optionalLocation.ifPresent(this::saveLocationToMonitor);
        model.addAttribute(ADD_LOCATION_FORM, new AddLocationForm());
        return "redirect:" + CONSOLE_VIEW_URL;
    }

    @PostMapping(value = CONSOLE_VIEW_URL + "/addProperty")
    public String addProperty(Model model, @Valid PropertyForm propertyForm,
                              BindingResult bindingResult) {
        initializeView(model);
        getUserDetails();
        if (bindingResult.hasErrors()) {
            model.addAttribute(ADD_LOCATION_FORM, new AddLocationForm());
            return CONSOLE_VIEW_TEMPLATE;

        }
        appPropertyService.saveProperty(propertyForm.getName(), propertyForm.getValue(), propertyForm.getDescription());
        model.addAttribute("appProperties", appPropertyService.getAllProperties());
        return "redirect:" + CONSOLE_VIEW_URL;
    }

    @PostMapping(value = CONSOLE_VIEW_URL + "/editProperty")
    public String editProperty(Model model, @Valid PropertyForm propertyForm,
                               BindingResult bindingResult) {
        initializeView(model);
        getUserDetails();
        if (bindingResult.hasErrors()) {
            initializeForms(model);
            return CONSOLE_VIEW_TEMPLATE;

        }
        Optional<AppPropertyEntity> propertyOptional = appPropertyService.getProperty(propertyForm.getName());
        propertyOptional.ifPresent(property -> appPropertyService.saveProperty(property.getProperty(), propertyForm.getValue(), propertyForm.getDescription()));
        model.addAttribute("appProperties", appPropertyService.getAllProperties());
        return "redirect:" + CONSOLE_VIEW_URL;
    }

    @PostMapping(value = CONSOLE_VIEW_URL + "/deleteProperty")
    public String deleteProperty(Model model, @RequestParam("id") int propertyId) {
        initializeView(model);
        getUserDetails();
        appPropertyService.deleteProperty(propertyId);
        model.addAttribute("appProperties", appPropertyService.getAllProperties());
        return "redirect:" + CONSOLE_VIEW_URL;
    }

    private void initializeForms(Model model) {
        model.addAttribute(ADD_LOCATION_FORM, new AddLocationForm());
        model.addAttribute(ADD_PROPERTY_FORM, new PropertyForm());
        model.addAttribute(EDIT_PROPERTY_FORM, new PropertyForm());
    }

    private void initializeView(Model model) {
        model.addAttribute("monitoredCoords", monitoredCoordinatesDaoService.getAll());
        model.addAttribute("appProperties", appPropertyService.getAllProperties());
    }

    private void saveLocationToMonitor(Location location) {
        User user = new User();
        user.setId(userDetails.getId());
        MonitoredCoordinatesEntity monitoredCoordinatesEntity = new MonitoredCoordinatesEntity(location, user);
        monitoredCoordinatesDaoService.save(monitoredCoordinatesEntity);
    }

    private void getUserDetails() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userDetails = ((UserDetailsImpl) principal);
        }
    }

}
