package org.tpokora.weather.model.entity;

import org.tpokora.common.model.AbstractEntity;
import org.tpokora.users.model.User;

import javax.persistence.*;

@Entity
@Table(name = "MONITORED_COORDINATES")
public class MonitoredCoordinatesEntity extends AbstractEntity {

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
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", longitudeDM=" + longitudeDM +
                ", latitudeDM=" + latitudeDM +
                ", user=" + user +
                '}';
    }
}
