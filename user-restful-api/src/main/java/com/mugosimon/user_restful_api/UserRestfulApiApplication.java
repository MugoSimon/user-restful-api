package com.mugosimon.user_restful_api;

import com.mugosimon.user_restful_api.utils.PortListener;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

@OpenAPIDefinition(
        info = @Info(
                title = "User - Restful - Api",
                version = "v1.0.0",
                description = "User - Restful - Api",
                contact = @Contact(
                        name = "Simon Wangechi",
                        email = "mugolastbon@gmail.com",
                        url = "https://github.com/mugosimon"),
                termsOfService = "http://swagger.io/terms/",
                license = @License(
                        name = "Apache 2.0",
                        url = "http://com.mugosimon"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Spring Boot User Management API",
                url = "https://github.com/mugosimon"
        )
)
@SpringBootApplication
public class UserRestfulApiApplication implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private PortListener portListener;

    public static void main(String[] args) {
        SpringApplication.run(UserRestfulApiApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        int port = portListener.getPort();
        System.out.println("***   ***   ***   ***   ***   ***   ***   ***   ***");
        System.out.println("User - Restful - ApiApplication Started\nrunning on port: " + port);
        System.out.println("***   ***   ***   ***   ***   ***   ***   ***   ***");
    }
}
