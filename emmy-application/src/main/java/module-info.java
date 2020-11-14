module emmy.application {
    requires emmy.common;
    requires emmy.persistance;
    requires emmy.config;

    requires spring.context;
    requires java.xml;
    requires javax.xml.soap.api;
    requires org.slf4j;
    requires spring.web;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires spring.security.core;
    requires java.sql;
    requires java.xml.bind;

    requires org.apache.logging.log4j;
    requires spring.boot;
    requires spring.tx;
    requires java.validation;
    requires spring.beans;
    requires spring.data.jpa;
    requires spring.boot.autoconfigure;
    requires emmy.domain;
    requires tomcat.embed.core;
    requires hibernate.jpa;
    requires spring.webmvc;

    exports org.tpokora.application;
    exports org.tpokora.application.rates;
    exports org.tpokora.application.weather.storms;
}