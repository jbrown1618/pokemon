package com.jbrown.pokemon.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static spark.Spark.port;

@Component
public class ApplicationConfiguration {

    @Autowired
    private RouteConfiguration routeConfiguration;

    public void configure() {
        port(8080);
        routeConfiguration.configure();
    }
}
