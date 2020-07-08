package org.tpokora.console.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.tpokora.users.model.User;
import org.tpokora.users.model.UserDetailsImpl;
import org.tpokora.weather.dao.MonitoredCoordinatesDaoService;
import org.tpokora.weather.model.Location;
import org.tpokora.weather.model.entity.MonitoredCoordinatesEntity;
import org.tpokora.weather.properties.OpenCageDataProperties;
import org.tpokora.weather.services.location.ILocationService;
import org.tpokora.weather.services.location.OpenCageDataLocationService;

import java.util.Optional;

import static org.tpokora.console.web.ConsoleViewConstants.*;

@Controller
public class ConsoleViewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleViewController.class);

    private UserDetailsImpl userDetails;

    private RestTemplate restTemplate;
    private OpenCageDataProperties openCageDataProperties;

    private ILocationService locationService;
    private MonitoredCoordinatesDaoService monitoredCoordinatesDaoService;

    public ConsoleViewController(RestTemplate restTemplate, OpenCageDataProperties openCageDataProperties, MonitoredCoordinatesDaoService monitoredCoordinatesDaoService) {
        this.restTemplate = restTemplate;
        this.openCageDataProperties = openCageDataProperties;
        this.monitoredCoordinatesDaoService = monitoredCoordinatesDaoService;
        locationService = new OpenCageDataLocationService(restTemplate, openCageDataProperties);
    }

    @GetMapping(value = CONSOLE_VIEW_URL, name = CONSOLE_VIEW)
    public String home(Model model) {
        LOGGER.info(">> Console");
        model.addAttribute("addLocationForm", new AddLocationForm());
        model.addAttribute("monitoredCoords", monitoredCoordinatesDaoService.getAll());
        getUserDetails();
        return CONSOLE_VIEW_TEMPLATE;
    }

    @PostMapping(value = CONSOLE_VIEW_URL + "/addLocation")
    public String addLocationMonitorByName(Model model, @ModelAttribute("locationForm") AddLocationForm locationForm) {
        LOGGER.info(">> Add Location to monitor by name: {}", locationForm.getLocationName());
        getUserDetails();
        Optional<Location> optionalLocation = locationService.getLocationCoordinatesByName(locationForm.getLocationName());
        optionalLocation.ifPresent(this::saveLocationToMonitor);
        model.addAttribute("addLocationForm", new AddLocationForm());
        return "redirect:" + CONSOLE_VIEW_URL;
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
            userDetails = ((UserDetailsImpl)principal);
        }
    }

    public class AddLocationForm {
        private String locationName;

        public String getLocationName() {
            return locationName;
        }

        public void setLocationName(String locationName) {
            this.locationName = locationName;
        }
    }
}
