module emmy.persistance {
    requires emmy.common;
    requires emmy.domain;

    requires spring.data.jpa;
    requires spring.data.commons;
    requires spring.tx;
    requires spring.context;
    requires hibernate.jpa;

    requires org.slf4j;

    exports org.tpokora.persistance.entity.users;
    exports org.tpokora.persistance.entity.weather;

    // Temporary repositories export
    exports org.tpokora.persistance.repositories.weather;
    exports org.tpokora.persistance.repositories.users;

    exports org.tpokora.persistance.services.rates;
    exports org.tpokora.persistance.services.weather;
}