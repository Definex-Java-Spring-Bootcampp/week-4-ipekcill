package com.patika.kredinbizdeservice.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

    @Value("${kredinbizde.openapi.url}")
    private String url;

    @Bean
    public OpenAPI myOpenAPI() {
        Server server = new Server();
        server.setUrl(url);
        server.setDescription("Server URL in Development environment");

        Contact contact = new Contact();
        contact.setEmail("ipekcill94@gmail.com");
        contact.setName("İpek Cil");
        contact.setUrl("https://www.ipekcil.com");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("KredinBizde API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage bank products.").termsOfService("https://www.ipekcil.com/terms")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(server));
    }
}
