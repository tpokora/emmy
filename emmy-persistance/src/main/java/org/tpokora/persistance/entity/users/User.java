package org.tpokora.persistance.entity.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.tpokora.persistance.entity.weather.MonitoredCoordinatesEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "USERS", schema = "public",
    uniqueConstraints =
        @UniqueConstraint(columnNames = { "USERNAME", "EMAIL" }))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="USER_ID")
    private int id;

    @Column(name = "USERNAME")
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "EMAIL")
    private String email;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    private Set<MonitoredCoordinatesEntity> monitoredCoordinateEntities;

    public User() {
    }

    public User(User user) {
        this.id = user.id;
        this.username = user.username;
        this.password = user.password;
        this.email = user.email;
        this.roles = user.roles;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<MonitoredCoordinatesEntity> getMonitoredCoordinateEntities() {
        return monitoredCoordinateEntities;
    }

    public void setMonitoredCoordinateEntities(Set<MonitoredCoordinatesEntity> monitoredCoordinateEntities) {
        this.monitoredCoordinateEntities = monitoredCoordinateEntities;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }
}
