module emmy.persistance {
    requires emmy.common;
    requires emmy.domain;
    requires java.persistence;
    requires spring.data.jpa;
    requires spring.data.commons;
    requires com.fasterxml.jackson.annotation;

    exports org.tpokora.persistance.entity.users;
    exports org.tpokora.persistance.entity.weather;

    exports org.tpokora.persistance.repositories.weather;
}