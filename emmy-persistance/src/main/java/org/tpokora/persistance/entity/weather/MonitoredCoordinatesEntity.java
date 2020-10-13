package org.tpokora.persistance.entity.weather;

import org.tpokora.persistance.entity.common.AbstractEntity;
import org.tpokora.domain.weather.Location;
import org.tpokora.persistance.entity.users.User;

import javax.persistence.*;

@Entity
@Table(name = "MONITORED_COORDINATES",
    uniqueConstraints =
        @UniqueConstraint(columnNames = { "LONGITUDE", "LATITUDE", "user_id"})
)
public class MonitoredCoordinatesEntity extends AbstractEntity {

    @Column(name = "LOCATION_NAME")
    private String locationName;
    @Column(name = "LONGITUDE", nullable = false)
    private Double longitude;
    @Column(name = "LATITUDE", nullable = false)
    private Double latitude;
    @Column(name = "LONGITUDE_DM", nullable = false)
    private Double longitudeDM;
    @Column(name = "LATITUDE_DM", nullable = false)
    private Double latitudeDM;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public MonitoredCoordinatesEntity() {}

    public MonitoredCoordinatesEntity(Location location, User user) {
        locationName = location.getName();
        longitude = location.getCoordinates().getLongitude();
        latitude = location.getCoordinates().getLatitude();
        longitudeDM = location.getCoordinates().getLongitudeDM();
        latitudeDM = location.getCoordinates().getLatitudeDM();
        this.user = user;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitudeDM() {
        return longitudeDM;
    }

    public void setLongitudeDM(Double longitudeDM) {
        this.longitudeDM = longitudeDM;
    }

    public Double getLatitudeDM() {
        return latitudeDM;
    }

    public void setLatitudeDM(Double latitudeDM) {
        this.latitudeDM = latitudeDM;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "MonitoredCoordinatesEntity{" +
                "id=" + id +
                ", locationName='" + locationName + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", longitudeDM=" + longitudeDM +
                ", latitudeDM=" + latitudeDM +
                ", user=" + user +
                '}';
    }
}
