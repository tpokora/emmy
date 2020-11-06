module emmy.application {
    requires emmy.common;
    requires emmy.persistance;

    requires spring.context;
    requires java.xml;
    requires javax.xml.soap.api;
    requires org.slf4j;
    requires spring.web;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires spring.security.core;
    requires java.sql;

    requires org.apache.logging.log4j;
    requires spring.boot;

    exports org.tpokora.application.rates;
    exports org.tpokora.application.weather.storms;
}