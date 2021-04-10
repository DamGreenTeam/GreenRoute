package com.sanvalero.greenroute.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Creado por @author: Javier
 * el 06/04/2021
 */
@Configuration
public class GreenRouteConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("GreenRoute API")
                        .description("Proyecto semana de prácticas 2ª evaluación")
                        .contact(new Contact()
                                .name("GreenTeam")
                                .email("greenteam@mail.com")
                                .url("https://github.com/DamGreenTeam/GreenRoute.git"))
                        .version("1.0"));
    }
}
