module emmy.application {
    requires emmy.common;
    requires emmy.persistance;

    requires spring.context;

    exports org.tpokora.services.rates;
}