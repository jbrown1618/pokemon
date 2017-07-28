package com.jbrown.pokemon.web;

import com.jbrown.pokemon.web.config.ApplicationConfiguration;
import com.jbrown.pokemon.web.config.WebContextConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(WebContextConfiguration.class);
        ctx.refresh();

        ApplicationConfiguration applicationConfiguration = ctx.getBean(ApplicationConfiguration.class);
        applicationConfiguration.configure();
    }
}
