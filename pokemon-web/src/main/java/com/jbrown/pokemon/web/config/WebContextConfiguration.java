package com.jbrown.pokemon.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan(basePackages = {
    "com.jbrown.pokemon"
})
@ImportResource({
    "config/species-config.xml",
    "config/moves-config.xml"
})
public class WebContextConfiguration {
}
