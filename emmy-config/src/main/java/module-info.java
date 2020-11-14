module org.tpokora.config {
    requires spring.context;
    requires spring.webmvc;
    requires spring.boot.autoconfigure;
    requires spring.tx;
    requires spring.boot;
    requires spring.security.config;
    requires spring.data.jpa;
    requires spring.beans;
    requires spring.security.core;
    requires spring.web;

    requires org.tpokora.persistance;

    exports org.tpokora.config.constants;
}