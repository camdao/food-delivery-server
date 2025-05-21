package com.delivery.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Value("${swagger.version}")
    private String version;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().components(new Components()).info(swaggerInfo());
    }

    private Info swaggerInfo() {
        License license = new License();
        license.setUrl("https://github.com/camdao/food-delivery-server");
        license.setName("Delivery Server Repository");

        return new Info()
                .version("v" + version)
                .title("\"Delivery Server API document\"")
                .description("This is the Delivery Server API documentation.")
                .license(license);
    }
}
