package org.tpokora.console.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.tpokora.console.dao.AppPropertyService;
import org.tpokora.console.web.forms.AddLocationForm;
import org.tpokora.console.web.forms.AddPropertyForm;
import org.tpokora.users.model.User;
import org.tpokora.users.model.UserDetailsImpl;
import org.tpokora.weather.dao.MonitoredCoordinatesDaoService;
import org.tpokora.weather.model.Location;
import org.tpokora.weather.model.entity.MonitoredCoordinatesEntity;
import org.tpokora.weather.properties.OpenCageDataProperties;
import org.tpokora.weather.services.location.ILocationService;
import org.tpokora.weather.services.location.OpenCageDataLocationService;

import javax.validation.Valid;
import java.util.Optional;

import static org.tpokora.console.web.ConsoleViewConstants.*;
import static org.tpokora.home.views.HomeViewConstants.HOME_VIEW_URL;

@Controller
public class ConsoleViewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleViewController.class);

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
        model.addAttribute("addLocationForm", new AddLocationForm());
        return "redirect:" + CONSOLE_VIEW_URL;
    }

    @PostMapping(value = CONSOLE_VIEW_URL + "/addProperty")
    public String addProperty(Model model, @Valid AddPropertyForm propertyForm,
                              BindingResult bindingResult) {
        initializeView(model);
        getUserDetails();
        if (bindingResult.hasErrors()) {
            model.addAttribute("addLocationForm", new AddLocationForm());
            return CONSOLE_VIEW_TEMPLATE;

        }
        model.addAttribute("addPropertyForm", new AddPropertyForm());
        return "redirect:" + CONSOLE_VIEW_URL;
    }

    private void initializeForms(Model model) {
        model.addAttribute("addLocationForm", new AddLocationForm());
        model.addAttribute("addPropertyForm", new AddPropertyForm());
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
