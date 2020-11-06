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

    exports org.tpokora.services.rates;
    exports org.tpokora.services.weather.storms;
}