package org.tpokora.application.weather.web;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.tpokora.application.weather.web.service.LocationAPIService;
import org.tpokora.domain.weather.Location;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LocationAPIController.class)
class LocationAPIControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LocationAPIService locationAPIService;

    @Test
    void testGetLocationCoordinatesSuccess() throws Exception {
        Location mockLocation = new Location(11.11, 22.22);
        mockLocation.setName("test location");
        when(locationAPIService.getLocationCoordinatesByName(anyString())).thenReturn(Optional.of(mockLocation));

        mockMvc.perform(get("/api/location?name=testLocation")
                .with(user("testUser").password("testPassword")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(mockLocation.getName())))
                .andExpect(jsonPath("$.coordinates.longitude", is(mockLocation.getCoordinates().getLongitude())))
                .andExpect(jsonPath("$.coordinates.latitude", is(mockLocation.getCoordinates().getLatitude())))
                .andExpect(jsonPath("$.coordinates.longitudeDM", is(mockLocation.getCoordinates().getLongitudeDM())))
                .andExpect(jsonPath("$.coordinates.latitudeDM", is(mockLocation.getCoordinates().getLatitudeDM())));
    }
}