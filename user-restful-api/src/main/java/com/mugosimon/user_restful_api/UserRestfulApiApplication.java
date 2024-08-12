package com.mugosimon.user_restful_api;

import com.mugosimon.user_restful_api.utils.PortListener;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

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
