package com.jbrown.pokemon;

import com.google.gson.Gson;
import com.jbrown.pokemon.config.RouteConfiguration;
import com.jbrown.pokemon.config.WebContextConfiguration;
import com.jbrown.pokemon.controllers.BattleController;
import com.jbrown.pokemon.controllers.SpeciesController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static spark.Spark.*;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(WebContextConfiguration.class);
        ctx.refresh();

        RouteConfiguration routeConfiguration = ctx.getBean(RouteConfiguration.class);
        routeConfiguration.configureRoutes();
    }
}
