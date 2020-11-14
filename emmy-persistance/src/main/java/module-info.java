module org.tpokora.persistance {
    requires org.tpokora.common;
    requires org.tpokora.domain;

    requires spring.data.jpa;
    requires spring.data.commons;
    requires spring.tx;
    requires spring.context;
    requires hibernate.jpa;

    requires org.slf4j;

    exports org.tpokora.persistance.entity.users;
    exports org.tpokora.persistance.entity.weather;
    exports org.tpokora.persistance.entity.console;

    exports org.tpokora.persistance.services.rates;
    exports org.tpokora.persistance.services.weather;
    exports org.tpokora.persistance.services.console;
    exports org.tpokora.persistance.repositories.users;
}